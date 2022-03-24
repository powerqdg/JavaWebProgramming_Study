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
 - HTTP요청: 요청라인, 요청헤더, 공백라인과 요청데이터
 - HTTP응답: 상태라인, 응답헤더, 공백라인과 응답데이터

#### HTTP 요청 형식
 - GET: 브라우저의 주소창에 직접 URL을 입력하거나 사용자가 링크를 클릭하는 경우 발생, 사용자가 입력한 데이터가 주소창에 그대로 노출되므로 로그인이나 결제정보를 보내면 안된다.
 - POST: 웹 서버에 데이터를 보낼 때 메시지 본문 부분에 붙여서 보냄, 보내는 데이터의 크기에 제한이 없다.

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
 - src: 자바 소스 파일을 두는 폴더
 - build/classes: 컴파일된 자바 클래스 파일(.class)이 놓이는 폴더
 - WebContent: HTML(.html), CSS(.css), JavaScript(.js), JSP, 이미지 파일 등 웹 콘텐츠를 두는 폴더
 - WebContent/WEB-INF: 웹 애플리케이션의 설정과 관련된 파일을 두는 폴더, 이 폴더에 있는 파일은 클라이언트에서 요청할 수 없음
 - WebContent/WEB-INF/web.xml: 웹 애플리케이션 배치 설명서(Deployment Descriptor)파일로 "DD파일"이라고도 부름
 - WebContent/WEB-INF/lib: 자바 아카이브(Archive) 파일(.jar)을 두는 폴더

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
