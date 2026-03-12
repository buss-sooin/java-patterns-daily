package designpatterns.structural.decorator.caching;

public class RealUserProfileService implements UserProfileService {

    @Override
    public UserProfile getProfile(Long userId) {
        System.out.println("DB 조회: userId=" + userId);
        return new UserProfile(userId, "사용자" + userId, "서울");
    }

}