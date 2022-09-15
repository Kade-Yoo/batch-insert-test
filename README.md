# 데이터 연동 모듈 성능 비교 예제
## Spring JDBC
- [Spring JDBC API Reference](https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/jdbc.html#jdbc-advanced-jdbc)
- Log를 통해 동작하는지 확인
- SimpleJdbcInsert를 통한 insert -> simpleJdbcInsert.executeBatch() 사용
- 10000건 insert : 112 msec
- 100000건 insert : 391ms

## Spring Data JDBC
- [Spring Data JDBC Reference](https://docs.spring.io/spring-data/jdbc/docs/2.4.2/reference/html/)
- batch size 1000 설정
- 10000건 insert : 431ms
- 100000건이 될 경우 로그를 찍지 않으면 정상동작함(리소스를 많이 먹어서 서버 터짐) : 1559 ms 
- Spring Data JPA와 동일한 성능을 보여주고 있음

## Mybatis
- [Mybatis](https://mybatis.org/mybatis-3/)
- 단건 insert 10000건 : 301ms
- 단건 insert 100000건 : 1108ms
- batch insert 10000건 : 378ms
- batch insert 100000건 : Out of memory. Java heap space
- SqlSessionFactory Executor.BATCH를 설정하여 batch insert를 진행했지만 100000건은 제대로 실행이 되지 않음 이유 확인 필요
- [참고1](https://devlog-wjdrbs96.tistory.com/200)
- [참고2](https://khj93.tistory.com/entry/MyBatis-MyBatis%EB%9E%80-%EA%B0%9C%EB%85%90-%EB%B0%8F-%ED%95%B5%EC%8B%AC-%EC%A0%95%EB%A6%AC)

## Spring Data JPA
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/2.4.2/reference/html/#reference)
- batch size 1000 설정
- 10000건 insert : 430 ms
- 100000건이 될 경우 로그를 찍지 않으면 정상동작함(리소스를 많이 먹어서 서버 터짐) : 1640ms
- JPA를 이용해서 Batch처리를 했지만 JPA가 추구하는 목적에 적합한 사용법일지 고민해볼 필요가 있다 만약 목적에 맞지 않다면 더 나은 모듈을 사용하는게 맞다라고 생각한다

## 난관 
1. Database Unit Test를 해본 경험이 없음
   - @SpringBootTest 를 사용하여 실제 어플리케이션이 구동되는 환경에서 테스트할 수 있도록 함

## Error
1. org.springframework.jdbc.datasource.init.CannotReadScriptException: Cannot read SQL script from class path resource [kr/kade/batchinserttest/dao/schema-h2.sql]; nested exception is java.io.FileNotFoundException: class path resource [kr/kade/batchinserttest/dao/schema-h2.sql] cannot be opened because it does not exist
- 원인 : SQL Annotation을 잘못 지정하여 오류가 발생
- 해결 : SQL Annotation 제거
2. Spring Data JPA, JDBC가 100,000건이 넘어가게 될 경우 뻗는 현상이 발생함
- 왜 멈추는지 궁금함 : 원인은 로그를 많이 찍어서 
3. org.apache.ibatis.binding.BindingException: Invalid bound statement (not found) : Mapper경로를 찾을 수 없는 현상
- mybatis mapper location path 설정함
- 원인을 추론했을 때 JavaConfig로 읽어들인 설정에 resources/mapper/*.xml을 읽어들이지 못해서 오류가 발생하는 것으로 보인다 (9/3)

## 추가 개발 및 공부 사항
- API 스트레스 테스트 구축(JMeter, nGrinder, k6 등)
[API 성능 테스트](https://ch4njun.tistory.com/266)
- Mybatis, JPA, QueryDSL를 모두 사용할 수 있는 환경 구축
- 각 Query수행할 때 로그 찍기
- Spring JDBC, Spring Data JDBC, Mybatis, Spring Data JPA 동작 원리 확인
- 각 모듈별 올바른 사용처 작성
- 또 다른 데이터 연동 모듈 서칭

## API 스트레스 테스트 Tool
- k6 : 
API 스트레스 테스트 툴(JMeter, nGrinder, k6, Gatling, Locust)을 분석하여 가장 적합한 것을 골랐다 
먼저, 스트레스 테스트의 목적이 아니기 때문에 러닝커브가 가장 낮고 경량화되있는 오픈소스를 선택하려고 하였다.
그렇게 종합하여 선택한 툴이 k6이다. k6를 이용하여 스트레스 테스트를 진행해보겠다.
- 테스트 파일 : k6/jpa-post-test.js, k6/jdbc-post-test.js, k6/mybatis-single-post-test.js, k6/mybatis-multi-post-test.js
- 4가지 데이터 연동 로직에 대해 테스트 진행하였으나 도출된 데이터를 어떻게 활용해야되는지 공부가 필요할 듯 하다.
- 스트레스 테스트를 하나의 API당 하나의 테스트 파일을 두는 것이 맞는지 생각해보자
- Virtual User 수가 10 -> 1000 요청 보내는 시간이 길어졌다. 당연한거다 사람이 많이 접근을 하면 부하가 걸리니까
- 또한, 실패률과 요청이 막히는 시간이 늘어났다.

## 마치며
- 데이터 연동 모듈별로 대량 insert를 테스트해 보았다.
- 처음 목적은 성적데이터 대량 insert 당시 가장 적합했을 법한 데이터 연동 모듈을 찾는 것이었다.
- 결론은 Spring 데이터 연동 모듈의 근본인 Spring JDBC를 사용하는 것이었다.
- 물론 단점은 있었다 Map Key, Value로 테이블의 컬럼명과 값을 맵핑 시켜줘야 한다는 점과 테이블명을 insert로직 전에 명시 해줘야 한다는 점
(단일 테이블만 사용하는 경우엔 테이블 별로 Dao를 나눠서 로직 구성을 한다면 테이블명 명시를 중복으로 구현하지 않아도 될 것 같다)
- private로 생성되기 때문에 자원 공유해서 초기화하는 일은 없어 보인다 -> 정확하지 않으므로 singleton pattern을 다시 찾아보자
- 로직 구현은 이렇게 하였고 추가로 Test코드와 API 성능 테스트 모듈까지 붙여보았다.
- 테스트 코드는 사실 아직까지 잘 사용하고 있다는 생각은 안들고 의무감에 쓰는 느낌이다. 테스트 코드가 유용하게 작용하기 위해선 통테를 진행해야 하고 빌드 시 전체 테스트를 실행하여 로직 변경 유무까지 확인하는게 좋아보인다. -> 추후 과제로 하자
- 그리고 API 성능 테스트는 사실... 맛만 본거다 어떻게 구성하는지만 제대로 뭐가 뭔지 파악하지 못했기 때문에 각 항목 별로 어떤 걸 나타내는지 파악하고 트래픽으로 인한 장애가 발생되지 않도록 대비책 알아보고 생각해보기로 하자 -> 트래픽으로 인한 장애에 대한 대비책