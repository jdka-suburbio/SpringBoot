package test.sis414.demo.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TokenBlackListService
{
    private List<String> tokenBlackList = new ArrayList<>();

    public void addToken(String token){
        tokenBlackList.add(token);
    }

    public boolean isBlackListToken(String token){
        Optional<String> resultToken = tokenBlackList.stream().filter(tkn -> tkn.equals(token)).findFirst();
        if(resultToken.isPresent()){
            return true;
        }
        return false;
    }
}
