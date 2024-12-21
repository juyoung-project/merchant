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
   
12/14
1.Cookie subdomain tenant에서 적재 안되는 이슈 발견 localStorage로 변경 작업 filter 및 보안 작업 진행예정

12/16
1.CORS 오류가 지속적으로 발생 해결필요

12/17
1. cors 오류 원인 파악 > SpringContext 적재안되는 원인 파악필요

12/18 오류원인 파악
1. 멀티테넌트 기반이라 해당 하는 스키마를 호출해야하는데 설정 오류로 인해 항상 PUBLIC스키마를 호출
2. 설정 변경을 통해 현재 URL.으로 접근되도록 수정


12.19 
1.cors 설정 점검 cors(cors -> cors.configurationSource(corsConfigurationSource)) 이게 적용되어야 진짜 cors 설정이 완료된거임 
2. db 호출전 TenantContext에 tenant적재하여 원하는 스키마.tabled로 접근하도록 수정

12.20
1. HikariCP 적용
2. ADMIN ROLE 체크 추가
3. 쿠키 사용으로 TOKEN 안정성 보장 추가
