# 자바 웹 개발 워크북 by 엄진영

## 2022.03.23
### 제1장 웹 애플리케이션의 이해
### 1.1 테스크톱 애플리케이션
#### 설명
PC에 설치되어 실행되는 애플리케이션으로서 실행 속도가 빠르다.

#### 실습학습내용
1. Java에서 기본으로 제공하는 Swing GUI 컴포넌트를 단순 윈도우 창을 만들 수 있다.
2. 윈도우 기능을 갖기 위해서는 JFrame클래스를 상속(extends) 받아야 한다.
3. 버튼의 클릭 이벤트를 처리하는 역할(리스너라고 부름)을 수행하기 위해서는 ActionListener인터페이스를 상속(implements)을 받아야한다.
4. ActionListener인터페이스를 상속(implements)을 받으면 actionPerformed를 구현해야 한다.

#### 문제점
1. 배포가 번거롭다.
2. 보안에 취약하다.

#### 해결방안
1. 배포가 번거로운 문제는 자동 갱신을 통해 해결.
2. 보안이 취약한 문제는 여전히 남아 있음.

### 1.2 클라이언트·서버 애플리케이션
#### 설명
애플리케이션의 기능을 클라이언트와 서버로 분리한다.
- 업무 변화에 대응하기 쉽다.
- 서버 쪽에서 데이터베이스에 접속 → 보안이 강화된다.

#### 실습학습내용
1. 요청 흐름 : CalculatorFrame → CalculatorAgent → CalculatorServer
2. 사칙연산 이외에 "%"를 추가 시, 비즈니스 로직을 담당하는 CalculatorServer에만 추가하면 된다.

#### 문제점
 한 번에 하나의 클라이언트 하고만 연결된다.

#### 해결방안
멀티 프로세스(Multi-process)와 멀티 스레드(Multi-thread) 기능 필요


### 1.3 다중 클라이언트의 요청 처리
#### 설명
스레드를 이용하여 다중 클라이언트의 요청을 처리한다.

#### 실습학습내용
1. 요청 흐름 : CalculatorFrame → CalculatorAgent → CalculatorServer → CalculatorWroker
2. CalculatorServer의 계산 작업을 CalculatorWroker(스레드)로 위임한다.

#### 문제점
프로그래밍의 복잡성 → 네트워크 프로그래밍, 스레드 프로그래밍 필요

#### 해결방안
비즈니스 로직, 사용자 접근 관리를 담당하는 애플리케이션 서버 도입

### 1.4 클라이언트·서버 아키텍처의 진화
#### 1. 전통적인 클라이언트·서버 아키텍처
서버는 데이터 처리를 맡고 클라이언트는 UI와 비즈니스 처리를 담당
→ 데이터 처리 부분을 공통화하여 PC에 분산되어 있는 자료를 하나의 서버에서 관리하면 자료의 중복이나 자료가 일치하지 않는 문제를 해소할 수 있다.
#### 2. 개선된 클라이언트·서버 아키텍처
클라이언트의 업무 처리 부분을 서버로 이관
→ 업무 처리를 전담하는 서버를 "애플리케이션 서버"라고 부르며, 클라이언트가 DB에 직접 접속하지 않기 때문에 DB 접속정보가 노출되는 사고를 막을 수 있음

### 1.5 웹 애플리케이션 아키텍처의 특징
#### 설명
웹 애플리케이션은 인터넷 환경을 기반으로 매우 유연한 애플리케이션 사용 환경을 제공한다.
- 클라이언트와의 통신은 웹 서버가 전담 → 네트워크 및 멀티 스레드 프로그래밍으로부터 탈출
- 애플리케이션  서버는 애플리케이션 실행 및 관리에 집중

## 2022.03.24
### 제2장 웹 프로그래밍 기초 다지기
### 2.1 HTTP 프로토콜의 이해
#### HTTP(Hyper-Text Transfer Protocol)
HTTP는 웹 브라우저와 웹 서버 사이의 데이터 통신 규칙

#### HTTP모니터링
- 웹 브라우저와 웹 서버 사이에 주고 받는 데이터를 들여다보기 위해서는 HTTP프록시 프로그램 필요.(Charles, Fiddler 등)
- 프록시 서버(Proxy Server)는 클라이언트와 서버 사이에 통신을 중계해주는 컴퓨터나 프로그램을 말함

#### HTTP 요청·응답
- HTTP요청 : 요청라인, 요청헤더, 공백라인과 요청데이터
- HTTP응답 : 상태라인, 응답헤더, 공백라인과 응답데이터

#### HTTP 요청 형식
- GET  : 브라우저의 주소창에 직접 URL을 입력하거나 사용자가 링크를 클릭하는 경우 발생, 사용자가 입력한 데이터가 주소창에 그대로 노출되므로 로그인이나 결제정보를 보내면 안된다.
- POST : 웹 서버에 데이터를 보낼 때 메시지 본문 부분에 붙여서 보냄, 보내는 데이터의 크기에 제한이 없다.

### 제3장 서블릿 프로그래밍
### 3.1 CGI프로그램과 서블릿
#### CGI프로그램
웹 브라우저, 웹 서버, 프로그램이 있을 때, 웹 브라우저와 웹 서버 사이에서는 HTTP문법이라면 웹 서버와 프로그램 사이에서는 CGI(Common Gateway Interface)라고 한다.

#### 서블릿(Servlet)
자바로 만든 CGI프로그램을 "서블릿(Servlet)"이라 한다. 자바 서블릿이 다른 CGI프로그램과 다른 점은, 웹 서버와 직접 데이터를 주고받지 않으며, 전문 프로그램에 의해 관리된다.

#### 서블릿 컨테이너(Servlet Container)
서블릿의 생성과 실행, 소멸 등 생명주기를 관리하는 프로그램을 말함, 서블릿 컨데이너가 서블릿을 대신하여 CGI규칙에 따라 웹 서버와 데이터를 주고 받는다. 
따라서 서블릿 개발자는 더 이상 CGI규칙에 대해 알 필요가 없다. 대신 서블릿 컨테이너와 서블릿 사이의 규칙을 알아야 한다.

### 3.2 Java EE, WAS
#### Java EE
- Java EE는 기능 확장이 쉽다.
- 이기종 간의 이식이 쉽다.
- 신뢰성과 보안성이 높다.
- 트랜잭션 관리와 분산 기능을 쉽게 구현할 수 있는 기능을 제공한다.

#### WAS(Web Application Server)
클라이언트 서버 시스템 구조에서 서버 쪽 애플리케이션의 생성과 실행, 소멸을 관리하는 프로그램을 "애플리케이션 서버(Application Server)"라 한다.
그 중에서 서블릿 컨테이너와 같이 웹 기술을 기반으로 동작되는 애플리케이션 서버를 "WAS(Web Application Server)"라 부른다.
특히 Java에서 말하는 WAS란, Java EE 기술 사양을 준수하여 만든 서버를 가리키며, 다른 말로 "Java EE 구현체"라 한다.
상용제품 : 티맥스소프트 제우스(JEUS), 오라클 웹로직(WebLogic), IBM 웹스피어(WebSphere), 레드햇 제이보스 엔터프라이즈(JBoss Enterprise) 등
오픈소스 : 레드햇 제이보스 AS, 오라클 GlassFish, 아파치재단 Geronimo 등

### 3.3 웹 프로젝트 준비
#### 웹 프로젝트 폴더 구조
- src : 자바 소스 파일을 두는 폴더
- build/classes : 컴파일된 자바 클래스 파일(.class)이 놓이는 폴더
- WebContent : HTML(.html), CSS(.css), JavaScript(.js), JSP, 이미지 파일 등 웹 콘텐츠를 두는 폴더
- WebContent/WEB-IN : 웹 애플리케이션의 설정과 관련된 파일을 두는 폴더, 이 폴더에 있는 파일은 클라이언트에서 요청할 수 없음
- WebContent/WEB-INF/web.xml : 웹 애플리케이션 배치 설명서(Deployment Descriptor)파일로 "DD파일"이라고도 부름
- WebContent/WEB-INF/lib : 자바 아카이브(Archive) 파일(.jar)을 두는 폴더

### 3.4 서블릿 만들기
서블릿 프로그래밍의 핵심은 Servlet 인터페이스를 이해하는 것, 보통 서블릿을 만들려면 HttpServlet 클래스를 상속받아야 한다고 생각하는 경우가 많다.

#### 실습학습내용
1. 서블릿 클래스는 반드시 javax.servlet.Servlet 인터페이스를 구현해야 한다.
2. Servlet 인터페이스에는 init(), service(), destory(), getServletInfo(), getServletConfig() 메서드가 있다.
3. 생명주기 관련 메서드: init(), service(), destory(), 생명주기 관련 이외 메서드: getServletInfo(), getServletConfig()
4. 서블릿을 만들었으면 web.xml에 배치 정보를 등록, 등록되지 않은 서블릿은 서블릿 컨테이너가 찾을 수 없다.
5. 서블릿 실행 시 init(), service()순으로 호출됨, 새로고침을 누르면 service()만 호출됨
 → 서블릿 컨테이너는 클라이언트로부터 요청을 받으면 해당 서블릿을 찾아보고, 만약 없으면 서블릿의 인스턴스를 생성함.
6. 서버 종료시 destory()가 호출됨
7. 웹 서버에게 요청할 때 서블릿 이름을 생략하고 디렉터리 위치까지만 지정한다면, 웹 서버는 해당 디렉터리에서 웰컴 파일을 찾아서 보내준다.
8. 디렉터리에 웰컴 파일이 여러 개 존재할 경우, web.xml의 "welcome-file-list"태그에 선언된 순서대로 찾는다.

### 3.5 웹 애플리케이션 배치
- Eclipse를 통한 자동 배치
- 웹 아카이브(Web Archive)파일 생성하여 배치

### 3.6 GenericServlet의 사용
GenericServlet 추상 클래스는 Servlet의 init(), destory(), getServletConfig(), getServletInfo()를 미리 구현하여 하위 클래스에게 공통의 필드와 메서드를 상속해 주고자 존재한다.
service()는 각 서블릿 클래스마다 별도로 구현해야 하기 때문에 GenericServlet에서는 구현하지 않는다.

#### 실습학습내용
1. service()의 매개변수로는 ServletRequest, ServletResponse가 있다.
2. ServletRequest 객체는 클라이언트의 요청 정보를 다룰 때 사용. (매개변수 추출, 클라이언트 IP주소 추출 등)
3. ServletResponse 객체는 응답과 관련된 기능을 제공. (인코딩 타입 설정, 문자집합 지정, 버퍼의 크기 조정, 출력 스트림 준비 등)
4. setCharacterEncoding() 출력할 데이터의 문자집합을 지정(기본값 ISO-8859-1) <br>
response.setContentType("text/plain"), response.setCharacterEncoding("UTF-8") → response.setContentType("text/plain;chartset=UTF-8")
5. Servlet 3.0 사양부터는 애노테이션으로 서블릿 배치 정보를 설정할 수 있다. @WebServlet("/calc")

### 제4장 서블릿과 JDBC
1. GenericServlet 클래스를 확장한 HttpServlet 클래스를 이용하여 서블릿 생성해봅니다.
2. 클라이언트의 요청을 GET과 POST 등으로 구분하여 처리하는 방법과 리다이렉트, 리프레시를 다루는 방법을 배웁니다.
3. 초기화 매개변수를 이용하여 설정 정보를 외부 파일에 두는 방법과 서블릿에서 이를 참고하는 방법을 알아봅니다.
4. 서블릿 실행 전, 후에 필터를 끼우는 방법을 배웁니다.

#### HttpServlet 클래스
1. HttpServlet 클래스는 GenericServlet 클래스의 하위 클래스이다.
2. HttpServlet을 상속받으면 GenericServlet 클래스를 상속받는 것과 마찬가지로 javax.servlet.Servlet 인터페이스를 구현한 것이 된다.
3. 클라이언트 요청이 들어오면, 첫째로 상속받은 HttpServlet의 service() 메서드가 호출되고, 둘째로 service()는 클라이언트의 요청 방식에 따라 doGet(), doPost(), doPut() 메서드를 호출한다. 따라서 HttpServlet를 상속받을 때 service() 메서드를 직접 구현하기보다는 클라이언트의 요청 방식에 따라 doXXX() 메서드를 오버라이딩 합니다.

#### JDBC
자바에서는 데이터베이스로부터 데이터를 가져오거나 입력, 수정, 삭제 등을 할 때 JDBC를 사용한다.
1. JDBC Type 1 - ODBC와 연결
- JDBC Type 1 드라이버는 ODBC 드라이버와의 연결을 제공한다.
- JRE에 기본으로 포함되며, Excel이나 Access 파일에 접속할 때 유용하다.
- ODBC 드라이버를 거치기 때문에 네 가지 유형의 드라이버 중 가장 속도가 느리다.
2. JDBC Type 2 - DBMS벤더 API 호출
- DBMS 벤더에서 제공하는 API를 사용한다.
- JDBC Type 2 드라이버는 별도로 내려받아야 한다.
3. JDBC Type 3 - 미들웨어 서버를 경우
- 미들웨어 서버를 거쳐 DBMS에 접속한다.
- 미들웨어 서버와 함께 제공되는 JDBC 드라이버를 사용한다.
- ODBC나 벤더 API처럼 C나 C++로 만든 API를 호출하지 않기 때문에 Pure Java라 한다.
4. JDBC Type 4 - DBMS 프로토콜로 직접 연결
- DBMS 전용 프로토콜을 사용하여 직접 통신을 한다.
- DBMS 벤더에서 제공하는 드라이버를 내려받아야 한다.
- ODBC나 벤더 API처럼 C나 C++로 만든 API를 호출하지 않기 때문에 Pure Java라 한다.

#### 실습학습내용
1. JDBC 프로그래밍의 첫 번째 작업은 DriverManager를 이용하여 java.sql.Driver 인터페이스 구현체를 등록하는 것.
2. DriverManager의 getConnection()을 호출하여 DB 서버에 연결할 수 있다. java.sql.Connection 인터페이스 구현체를 반환한다.
3. java.sql.Connection 인터페이스 구현체의 주요 메서드 중 createStatement(), prepareStatement(), prepareCall()는 SQL문을 실행하는 객체를 반환하고 commit(), rollback()은 트랜잭션 처리를 수행하는 메서드이다.
4. createStatement()는 java.sql.Statement 인터페이스의 구현체를 반환한다.
5. java.sql.Statement 인터페이스의 구현체의 주요 메서드는 아래와 같다.
- executeQuery() : 결과가 만들어지는 SQL문을 실행할 때 사용한다. 보통 SELECT문을 실행
- executeUpdate() : DML과 DDL 관련 SQL문을 실행할 때 사용한다.(DML: INSERT, UPDATE, DELETE / DDL: CREATE, ALTER, DROP)
- execute() : SELECT, DML, DDL 명령문 모두에 사용 가능하다.
- executeBatch() : addBatch()로 등록한 여러 개의 SQL문을 한꺼번에 실행할 때 사용한다.
6. executeQuery()는 java.sql.ResultSet 인터페이스의 구현체를 반환한다. 이 객체를 통해 서버에서 질의 결과를 가져올 수 있다.
- first(), last(), previous(), next(), getXXX(), updateXXX(), deleteRow()
- SELECT MNO, MNAME FROM MEMBERS → SELECT getInt(1), getString(2) FROM MEMBERS 또는 SELECT getInt("MNO"), getString("MNAME") FROM MEMBERS
7. (중요!)JDBC 프로그래밍을 할 때 주의할 점은 정상적으로 수행되든 오류가 발생하든 간에 반드시 자원 해제를 수행하는 것이다.
8. DriverManager와 Connection, PreparedStatement 모두 java.sql 패키지에 소속되어 있는데, 간혹 임포트(import) 코드를 자동 생성하는 과정에서 com.sum.corba...와 같은 엉뚱한 패키지를 임포트 하는 경우가 종종 있다. 주의하자.
9. PreparedStatement는 반복적인 질의를 하거나, 입력 매개변수가 많은 경우에 유용. 이미지와 같은 바이너리 데이터를 저장, 변경할 때는 PreparedStatement만이 가능하다. 
10. 웹 브라우저에서 보낸 한글 데이터를 서블릿에서 꺼낼 때 글자가 깨지는 경우가 있다. 서블릿에서 getParameter()을 호출하기 전에 request.setCharacterEncoding()을 하자.
11. GET요청으로 받은 한글이 깨지는 경우. server.xml에서 URIEncoding="UTF-8"을 추가하자.

#### 리프래시
일정 시간이 지나고 나서 자동으로 서버에 요청을 보내는 방법
- 응답 헤더를 이용한 리프래시
- HTML의 meta 태그를 이용한 리프래시

#### 리다이렉트
클라이언트로 응답 결과를 출력하지 않고 즉시 다른 url로 이동하게 하는 방법, sendRedirect()

#### 서블릿 초기화 매개변수
1. 서블릿을 생성하고 초기화할 때, 즉 init()를 호출할 때 서블릿 컨테이너가 전달하는 데이터로 보통 데이터베이스 연결 정보와 같은정적인 데이터를 서블릿에 전달할 때 사용
2. web.xml에 설정하는 방법과 애노테이션을 사용하여 설정하는 방법이 있다.
3. 이전 자바 코드 안에 JDBC드라이버 클래스를 직접 작성했는데, Class.forName(this.getinitParameter("driver"))로 바꾸면 web.xml에서만 수정하면 되기 때문에 유지보수가 편하다.

#### 컨텍스트 초기화 매개변수
같은 웹 애플리케이션에 소속된 서블릿들이 공유하는 매개변수

#### 필터
필터는 서블릿 실행 전후에 어떤 작업을 하고자 할때 사용하는 기술
1. 용도
- 로그출력
- 사용자 인증 및 권한 검사
- 암호화 및 복호화
- 데이터 압축 및 해제
- 이미지 변환 등
2. javax.servlet.Filter 인터페이스를 구현해야 한다. init(), destory(), doFilter()
3. web.xml에 설정하는 방법과 애노테이션을 사용하여 설정하는 방법이 있다.

## 2022.03.26
### 제5장 MVC 아키텍처
### 5.1 MVC 이해하기
MVC란 모델(model), 뷰(videw), 컨트롤러(controller)로 역할이 분리되어 있는 구조로서 클라이언트의 요청 처리를 세개의 컴포넌트가 나누어 처리

#### MVC의 각 컴포넌트 역할
1. 컨트롤러(controller) 컴포넌트의 역할 : 클라이언트의 요청을 받았을 때 그 요청에 대해 실제 업무를 수행하는 모델 컴포넌트를 호출하는 일을 함.
2. 모델(model) 컴포넌트의 역할 : 데이터 저장소와 연동하여 사용자가 입력한 테이터나 사용자에게 출력할 데이터를 다루는 일을 함.
3. 뷰(view) 컴포넌트의 역할 : 모델이 처리한 데이터나 그 작업 결과를 가지고 사용자에게 출력할 화면을 만드는 일을 함.

### 5.2 뷰 컴포넌트와 JSP
MVC아키텍처에서 뷰 컴포넌트를 만들 때 보통 JSP를 사용한다. 뷰 컴포넌트의 역할은 웹 브라우저가 출력할 화면을 만드는 일을 하고, JSP는 화면 생성을 쉽게 해주는 기술이라고 볼 수 있다.

#### JSP 구동원리
1. 웹 브라우저가 Hello.jsp를 요청
2. JSP 엔진이 HttpJspPage 인터페이스를 구현한 Hello_jsp.java 소스를 생성
3. 자바 컴파일러가 Hello_jsp.java를 컴파일하여 Hello_jsp.class를 생성

### 5.3 JSP의 주요 구성 요소
1. 템플릿 데이터
2. JSP 전용 태그 - 지시자 : 지시자(page, taglib, include)나 속성에 따라 특별한 자바코드를 생성, <%@ 지시자 %>
3. JSP 전용 태그 - 스크립트릿 : JSP페이지 안에 자바 코드를 넣을때 사용, <% 자바코드 %>
4. JSP 전용 태그 - 선언문 : 서블릿 클래스의 멤버(변수나 메서드)를 선언할 대 사용, <%! %>
5. JSP 전용 태그 - 표현식 : 문자열을 출력할 때 사용, <%= 결과 반환하는 자바 코드 %>

### 5.4 서블릿에서 뷰 분리하기


