package designpatterns.creational.builder;

// 프-인-셋-리-빌-토-위 완성판
public class OriginalObject {

    // [중요] final 필드는 생성자에서 반드시 초기화되어야 합니다.
    private final int num;
    private final String word;
    private final SomeType type;
    private final IPatterns pattern;

    // [프] private 생성자: 빌더로부터 재료를 받아 '불변 객체'를 완성합니다.
    private OriginalObject(Builder builder) {
        this.num = builder.num;
        this.word = builder.word;
        this.type = builder.type;
        this.pattern = builder.pattern;
    }

    // [빌] 정적 시작점: 누구나 이 문을 통해 빌더를 시작합니다.
    public static Builder builder() {
        return new Builder();
    }

    // [토] 복합 수정용: 현재 객체를 다시 작업대(Builder)로 올립니다.
    public Builder toBuilder() {
        return new Builder()
                .num(this.num)
                .word(this.word)
                .type(this.type)
                .pattern(this.pattern);
    }

    // [위] 단일 수정용: 특정 값만 바꿔서 바로 새 객체를 줍니다. (toBuilder 재활용)
    public OriginalObject withNum(int num) {
        return this.toBuilder()
                .num(num)
                .build();
    }

    // [인] static 내부 클래스: 메모리 누수 방지용
    public static class Builder {
        private int num;
        private String word;
        private SomeType type;
        private IPatterns pattern;

        // 기본값 설정은 빌더 필드에서 하는 것이 실무 스타일입니다.
        public Builder() {
            this.num = 0;
            this.word = "Default";
            this.type = SomeType.A;
        }

        // [셋] 메서드 체이닝을 위해 this를 반환합니다.
        public Builder num(int num) {
            this.num = num; return this;
        }
        public Builder word(String word) {
            this.word = word; return this;
        }
        public Builder type(SomeType type) {
            this.type = type; return this;
        }
        public Builder pattern(IPatterns pattern) {
            this.pattern = pattern; return this;
        }

        // [리] 핵심! 빌더가 아니라 'OriginalObject'를 새로 찍어내야 합니다.
        public OriginalObject build() {
            // 유효성 검사(Validation)
            if (pattern == null) throw new IllegalStateException("Pattern은 필수입니다!");
            return new OriginalObject(this);
        }
    }
}