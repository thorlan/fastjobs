package br.com.fastjobs.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {
//esse generic é o tipo de dado que quero interceptar, se quiser pegar um responsebody que contenha um "usuario" so passar ele aqui.
	
	//só vou executar o beforeBodyWrite quando o support retornar true!
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		
		String refreshToken = body.getRefreshToken().getValue();
		
		adicionarRefreshTokenAoCookie(refreshToken, httpServletRequest, httpServletResponse);
		removerRefreshTokenDoBody(token);
		
		return body;
	}

	private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void adicionarRefreshTokenAoCookie(String refreshToken, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(false); //TODO: MIGRAR PARA TRUE EM PRODUCAO
		refreshTokenCookie.setPath(httpServletRequest.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(60*24*30);
		httpServletResponse.addCookie(refreshTokenCookie);
		
	}

}
