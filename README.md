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
  클래스에 인스턴스가 하나만 존재해야 하며, 그 인스턴스에 대한 전역 접근점을 제공한다.  
  핵심은 통제된 전역 상태(single point of access + single instance) 제공.

  **실무**  
  애플리케이션 전역에서 동일한 상태를 공유해야 하는 자원(설정, 캐시, 로거, 연결 풀 등)을 관리.  
  초기화 비용 1회, 메모리 절약, 상태 일관성 보장.

  UML:
  ```mermaid  
  classDiagram  
    class Singleton {  
      +getInstance() Singleton  
      -Singleton()  
    }  