2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* 서버의 root로 접근하면 web.xml의 <welcom-file-list>에 따라 index.jsp로 이동, index.jsp는 "/list.next"로 redirect한다.

* ~.next로 끝나는 주소는 annotation을 통해 core.mvc.FrontController로 연결된다.

* FrontController는 request의 URI(/list.next)를 받아오고, RequestMapping을 통해 이 URI에 맞는 controller인 ListController를 받는다.

* ListController.excute()를 실행하면, 먼저 QuestionDao의 findAll method를 수행한다. 

* JdbcTemplate으로 connection을 생성하고, QUESTIONS 테이블에 있는 모든 레코드를 questionId 내림차순에 따라 resultSet에 가져와 question List를 생성하고 return한다.

* ListController.excute method는 위의 question List를 받고, ModelAndView를 생성, view를 question List를 출력할 list.jsp로 설정하고, 데이터인 question List를 model에 저장 한다. 

* FrontController는 forward할 주소(view)를 받고, view.render method를 호출한다.

* view.render()에서 주소는 "redirect:"나 "api"로 시작하지 않기 때문에 그대로 RequestDispatcher에 이 주소를 설정, questionList를 setAttribute하고 forward를 통해 list.jsp로 이동한다.  