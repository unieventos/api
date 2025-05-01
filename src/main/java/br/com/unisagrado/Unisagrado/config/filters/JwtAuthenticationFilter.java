package br.com.unisagrado.Unisagrado.config.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import br.com.unisagrado.Unisagrado.config.security.JwtUtil;
import br.com.unisagrado.Unisagrado.unieventos.auth.exception.AccessTokenNotFound;
import br.com.unisagrado.Unisagrado.unieventos.auth.exception.TokenExpiredException;
import br.com.unisagrado.Unisagrado.unieventos.auth.model.Profile;
import br.com.unisagrado.Unisagrado.unieventos.auth.service.ProfileService;
import br.com.unisagrado.Unisagrado.unieventos.auth.validator.ProfileValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;

	private UserDetailsService userDetailsService;

	private ProfileService profileService;

	private HandlerExceptionResolver handlerExceptionResolver;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService,
			ProfileService profileService, HandlerExceptionResolver handlerExceptionResolver) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.profileService = profileService;
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		Profile profile = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);

			try {
				profile = profileService.findProfileByToken(jwt);
				username = jwtUtil.extractUsername(profile.getToken());

				ProfileValidator.profileExpired(profile);
			} catch (AccessTokenNotFound ex) {
				handlerExceptionResolver.resolveException(request, response, null, ex);
				throw ex;
			} catch (TokenExpiredException ex) {
				profileService.revokeToken(profile.getToken());
				handlerExceptionResolver.resolveException(request, response, null, ex);
				throw ex;
			}

		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(profile.getToken(), userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				profileService.refreshProfile(profile);
			}
		}

		filterChain.doFilter(request, response);
	}
}
