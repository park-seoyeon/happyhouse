package com.ssafy.happyhouse.interceptor;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happyhouse.model.service.JwtService;

@Component
@CrossOrigin("*")
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
    private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	logger.info(request.getMethod() + " : " + request.getServletPath());
    	String RType = new StringTokenizer(request.getServletPath(), "/").nextToken();
    	// request의 parameter에서 auth_token으로 넘어온 녀석을 찾아본다.
    	// String token = request.getParameter("auth_token");
    	String token = request.getHeader("auth-token");
    	if(token == null || "null".equals(token) || "undefined".equals(token))
    		token = null;
//    	System.out.println("Request Type : " + RType);
//    	System.out.println("Method Type : " + request.getMethod());
//    	System.out.println("token : " + token);
    	// request 타입이 confirm일 때(로그인)
    	if("confirm".equals(RType)) {
	        // option 요청은 바로 통과시켜주자.
	        if (request.getMethod().equals("OPTIONS")) {
	            return true;
	        } else {
	        	// 여기 부분이 Get, Post, Put, Delete 요청에 대한 처리이다
	            if (token != null && token.length() > 0) {
	                // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다.
	                jwtService.checkValid(token);
	                logger.info("토큰 사용 가능 : {}", token);
	                return true;
	            } else {
	                throw new RuntimeException("인증 토큰이 없습니다.");
	            }
	        }
    	}
    	// request 타입이 user일 때(회원 관리)
    	else if("user".equals(RType)) {
	        if (request.getMethod().equals("OPTIONS")) {
	            return true;
	        }
	        else {
	            if (token != null && token.length() > 0) {
	                jwtService.checkValid(token);
	                logger.info("토큰 사용 가능 : {}", token);
	                return true;
	            } else {
	            	// user관련 request 중에서 user를 등록하는 부분인 POST method는 로그인을 하지 않은 상태에서 이루어져야 한다.
	            	if(request.getMethod().equals("POST")) {
	            		return true;
	            	} else {
	            		throw new RuntimeException("인증 토큰이 없습니다.");
	            	}
	            }
	        }
    	}
    	// request 타입이 board일 때(게시판 관리)
    	else if("board".equals(RType)) {
	        if (request.getMethod().equals("OPTIONS")) {
	            return true;
	        } else {
	            if (token != null && token.length() > 0) {
	                jwtService.checkValid(token);
	                logger.info("토큰 사용 가능 : {}", token);
	                return true;
	            } else {
	            	// board관련 request 중에서 게시글을 보는 부분인 GET method는 로그인을 하지 않은 상태에서 이루어져야 한다.
	            	if(request.getMethod().equals("GET")) {
	            		return true;
	            	} else {
	            		throw new RuntimeException("인증 토큰이 없습니다.");
	            	}
	            }
	        }
    	}
    	else if("comment".equals(RType)) {
    		if (request.getMethod().equals("OPTIONS")) {
    			return true;
    		} else {
    			if (token != null && token.length() > 0) {
    				jwtService.checkValid(token);
    				logger.info("토큰 사용 가능 : {}", token);
    				return true;
    			} else {
    				// comment관련 request 중에서 댓글을 보는 부분과 댓글을 쓰는 부분인 GET, POST method는 로그인을 하지 않은 상태에서 이루어져야 한다.
    				if(request.getMethod().equals("GET") || request.getMethod().equals("POST")) {
    					return true;
    				} else {
    					throw new RuntimeException("인증 토큰이 없습니다.");
    				}
    			}
    		}    		
    	}
    	// 나머지 경우(일단 Map 관련은 토큰이 필요 없다. likearea는 vue에서 비로그인시 접근을 막았다.)
    	else {
    		return true;
    	}
    }
}
