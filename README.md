# 멀티테넌트 기반 가맹점관리
 사용기술 및 스택
 - java 17 version
 - spring boot 3.7, postgresql


12/05 구현내용
1. 기본 프론트 페이지 간단한 가맹점 crud
2. 서버 스프링시큐리티 설정
3. 서브도메인을 활용한 멀티테넌트 적용
   
12/09 구현내용
1. 어드민 페이지에서 스키마를 복사하여 테넌트 생성 기능 추가
2. 서브도메인을 이용하여 페이지 복제 가능

12/11 구현내용
1. 어드민 페이지에서 스키마에 있는 데이터 복사기능추가
2. 스키마 비활성화 기능 존재하지 않거나 없는 스키마 요청시 404 페이지로 보내기

12/13 구현내용
1. 멀티테넌트에서 쿠키 세팅이 작동하지 않음 ( 해결필요 )
