package designpatterns.structural.decorator.caching;

public class CachingDecoratorRunner {

    public static void main(String[] args) {
        UserProfileService service = new CachingDecorator(new RealUserProfileService());

        System.out.println(service.getProfile(1L));
        System.out.println(service.getProfile(1L)); // 캐시 히트

        ((CachingDecorator) service).invalidate(1L);
        System.out.println(service.getProfile(1L)); // 다시 DB 조회
    }

}