# Merchant Management System

> 멀티테넌트를 활용한 SaaS형 가맹점 관리 시스템

## 사용한 기술
![Vue](https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vue.js&logoColor=4FC08D)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

## 목차
- [소개](#소개)
- [구현내용](#구현내용)
- [프로젝트 로컬 설정 방법](#프로젝트-로컬-설정-방법)
- [필수 요구 사항](#필수-요구-사항)
- [추후 작업](#추후작업)
- [연락처](#연락처)

## 소개
멀티테넌트 기반의 SaaS형 가맹점 관리 시스템으로 기업들이 효율적으로 가맹점을 관리할 수 있도록 돕습니다

- **확장성**: 관리자는 데이터베이스 구조를 복제하여 각 기업에 맞는 서브도메인 (예: github.merchant.com, notion.merchant.com)을 설정할 수 있습니다.
- **편리성**: 미리 구축된 템플릿을 사용하면 관리자는 서브도메인별로 데이터베이스 테이블과 데이터를 손쉽게 복제하여 빠르고 효율적으로 가맹점 관리 시스템을 구축할 수 있습니다.
- **안정성**: 각 가맹점의 데이터는 독립적인 서브도메인에서 관리되어 데이터 격리와 보안이 강화됩니다. HikariCP를 활용하여 안정적인 DB 연결을 보장합니다.

## 구현내용 
- Spring security + Jwt를 활용한 로그인
- Subdomain을 활용한 멀티테넌트 구성
- hikariCp 적용하여 서비스 안정성 구성
- 일반사용자와 시스템 관리자의 권한 기능 구현
- 테이블 구조 및 데이터를 복제할 수 있는 기능 구현

## 프로젝트 로컬 설정 방법
1. 프로젝트 클론 ( https://github.com/juyoung-project/merchant.git )
2. gradle lib 설치
3. application.properties 설정
4. DB테이블 설정
5. host파일 수정 ( 현재 *.merchant.com:8080 으로 설정 )
6. front/merchant 폴더 내부에서 npm install 진행 ( node.js v22.11.0 사용 )
7. 프론트서버 실행 npm run serve
8. 서버 실행 8080포트로 실행

## 필수 요구 사항
- JDK 17 이상
- Node.js v22.11.0 이상
- SpringBoot 3.3 이상
- SpringSecurity 6.3 이상
- PostgreSQL 데이터베이스
- Gradle 빌드 도구

## 추후작업
- 가맹점 및 사용자 마이그레이션 기능 추가
- 이메일 및 SMS 알림 시스템 구축
- 정산 기능 구현
- 통계 대시보드 개발
- CI/CD 파이프라인 설정

## 연락처
- 이메일: [kjo8830@gmail.com](mailto:kjo8830@gmail.com)
