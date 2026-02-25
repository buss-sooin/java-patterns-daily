# java-patterns-daily
Java Practical Design Patterns with Spring
실무에서 자주 마주치는 상황을 중심으로 GoF 디자인 패턴을 직접 구현하고,
실무 환경에서 자연스럽게 활용하는 방식을 함께 보여주는 학습 & 포트폴리오 프로젝트입니다.

목표
* GoF 23개 패턴을 단순히 교과서 예제 수준으로 끝내지 않고
  실무에서 왜/어떻게/언제 쓰는지 코드와 주석으로 기록
* 실무 통합 방식 중점적으로 다룸

## Creational Patterns
객체 생성 로직 캡슐화.

- **[Singleton](https://github.com/buss-sooin/java-patterns-daily/tree/main/src/designpatterns/creational/singleton)**:  
  **의도**  
  클래스의 인스턴스가 오직 하나만 존재하도록 보장하고, 이에 대한 전역적인 접근점을 제공한다.  
  애플리케이션 전체에서 공유되어야 하는 상태나 리소스를 일관되게 관리할 수 있게 한다.

  **실무**  
  설정 정보, 캐시, 연결 풀, 로거 등 전역적으로 공유해야 하는 자원을 안전하게 관리.  
  EnumSingleton을 주로 사용하며, 보안 민감 정보 관리에 가장 적합.

  UML:

- **[Builder](https://github.com/buss-sooin/java-patterns-daily/tree/main/src/designpatterns/creational/builder)**:  
  **의도**  
  복잡한 객체를 단계적으로 구성할 수 있게 하면서, 생성 과정과 표현을 분리한다.  
  클라이언트가 객체의 내부 표현을 알지 못해도 다양한 표현을 생성할 수 있게 한다.

  **실무**  
  Fluent Interface + Nested Builder + Step Builder + Wither + Functional Builder를 조합하여  
  API 설정 객체, 요청 DTO, 테스트 픽스처 등 복잡한 객체 생성에 광범위하게 사용.

  UML:

- **[Simple Factory](https://github.com/buss-sooin/java-patterns-daily/tree/main/src/designpatterns/creational/simplefactory)**:  
  **의도**  
  객체 생성 로직을 한 곳에 집중시켜 캡슐화한다.  
  클라이언트가 구체 클래스에 의존하지 않고 타입으로만 객체를 얻을 수 있게 한다.

  **실무**  
  간단한 타입 기반 객체 생성(결제 방식, 알림 타입 등)에 가장 많이 사용.  
  Provider와 결합하여 중앙 집중화.

  UML:

- **[Factory Method](https://github.com/buss-sooin/java-patterns-daily/tree/main/src/designpatterns/creational/factorymethod)**:  
  **의도**  
  객체 생성을 서브클래스에 위임하여 클라이언트가 구체 클래스에 의존하지 않게 한다.  
  새로운 구현체 추가 시 기존 코드를 수정하지 않고 확장할 수 있게 한다.

  **실무**  
  결제, 알림, 리포트 등 전략이 자주 변경되는 도메인에서 확장성 있게 사용.  
  Provider와 함께 런타임 전략 선택에 활용.

  UML:

- **[Abstract Factory](https://github.com/buss-sooin/java-patterns-daily/tree/main/src/designpatterns/creational/abstractfactory)**:  
  **의도**  
  관련된 객체들의 제품군을 생성하는 인터페이스를 제공한다.  
  클라이언트가 구체적인 제품 클래스에 의존하지 않고 전체 제품군을 한 번에 교체할 수 있게 한다.

  **실무**  
  UI 테마, 플랫폼별 설정, DB/캐시 제품군 등 관련 객체를 통째로 교체해야 할 때 사용.  
  Provider와 결합하여 런타임 테마 스위칭에 활용.

  UML:

## Structural Patterns
(진행 예정)

## Behavioral Patterns
(진행 예정)