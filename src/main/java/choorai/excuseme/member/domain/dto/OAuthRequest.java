package choorai.excuseme.member.domain.dto;


import lombok.Getter;

@Getter
public class OAuthRequest {

    private String socialLoginType;

    private String accessToken;

}