package choorai.excuseme.oauth.ui;

import choorai.excuseme.member.application.dto.LoginResponse;
import choorai.excuseme.oauth.application.OauthService;
import choorai.excuseme.oauth.domain.dto.OAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/login/{loginType}")
    public ResponseEntity<LoginResponse> oauthLogin(
            @PathVariable("loginType") String socialLoginType,
            @RequestBody OAuthRequest oAuthRequest) {
        final LoginResponse oAuthResponse = oauthService.oAuthLogin(socialLoginType, oAuthRequest);
        return new ResponseEntity<>(oAuthResponse, HttpStatus.OK);
    }
}
