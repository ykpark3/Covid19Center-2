# 🏥 Covid-19 Center


감염 위험 낮추고 진료 속도 높이는 선별진료소 서비스 ‘Covid19 Center’

![Untitled](https://user-images.githubusercontent.com/59998914/126848498-ca707a13-bdcb-4e2a-a32b-aa0b2ed3303e.png)

## 💡 Background


- 코로나19 신규 확진자 지속 발생
- 선별 진료소 대기 시 불필요한 접촉
- 불편한 선별진료소 시스템

## **📚 Stack & Library**

- OS: Windows 10
- Android/Java
- Room : 안드로이드 로컬 DB 사용
- DBMS: MySQL - Paas-ta 클라우드 서비스 사용
- Node.js : 개발 플랫폼
- Express.js
- Naver maps API
- COVID-19 API

## 👩‍💻 Project Features


## 1. 선별진료소 정보 제공 기능

선별진료소 API에서 받아온 선별진료소의 주소, 전화번호 등의 정보를 제공한다.

Naver Map API를 통해 선별 진료소 위치를 받아오고 사용자의 현재 위치와 비교해서 사용자에게 가까운 순으로 선별 진료소를 보여준다. 이를 통해 사용자는 선별진료소를 쉽게 찾을 수 있다.

## 2. 예약 기능

예약을 접수하면 예약 날짜, 예약 시간, 예약자 정보, 문진표 내용이 서버에 저장된다.

의료진이 진료 예약 시간을 ‘Covid19 Center’에서 설정하면 서버를 통해 저장되어 방문자의 밀집도를 분산시킬 수 있다.

의료진은 서버를 통해 환자의 진료 여부를 확인하고 데이터를 관리할 수 있다.

## 3. QR 코드 기능

서버에 저장된 예약 정보와 문진표는 QR 코드로 변환하여 사용자에게 제공된다

문진표에서는 방문 국가, 확진자 접촉여부, 증상, 특이사항 등을 작성한다.

선별진료소에서 의료진이 QR 코드를 스캔하면 손쉽게 예약을 확인할 수 있다.

## 🎞️ Service UI


## 사용자 서비스

## 1. 홈 화면 및 선별 진료소 목록

![Untitled 1](https://user-images.githubusercontent.com/59998914/126848499-82d0e350-a23f-4a70-a0d7-5d68ac56a33d.png)

![Untitled 2](https://user-images.githubusercontent.com/59998914/126848500-93e01433-e2e1-4969-95c9-4b01272369df.png)

![Untitled 3](https://user-images.githubusercontent.com/59998914/126848502-90a21fb2-74ef-4290-a342-50f19c92346f.png)

## 2. 선별진료소 상세정보 및 예약 페이지

![Untitled 4](https://user-images.githubusercontent.com/59998914/126848505-a7c4f9f8-5160-447d-80a3-f3a8777db3bb.png)

![Untitled 5](https://user-images.githubusercontent.com/59998914/126848510-eea359b9-a56a-4aa9-815f-74c445aba085.png)

![Untitled 6](https://user-images.githubusercontent.com/59998914/126848511-e5b0ea36-1f32-4862-b2d6-972d362ea573.png)

## 3. 자가 진단 페이지

![Untitled 7](https://user-images.githubusercontent.com/59998914/126848513-0898a9f4-8845-47f3-a046-7aeab9af1afc.png)

![Untitled 8](https://user-images.githubusercontent.com/59998914/126848514-a9c2c4c5-0bae-4da7-8ea7-54560fd7f0e6.png)

## 4. 마이페이지

![Untitled 9](https://user-images.githubusercontent.com/59998914/126848515-5b7f779a-465b-4154-9c78-6938a5d5bff9.png)


## 의료진용 서비스

## 5. 의료진용 QR 스캔 페이지

![Untitled 10](https://user-images.githubusercontent.com/59998914/126848516-c800dc53-c216-4159-ad72-779a0920fb8f.png)

## 6. 의료진용 예약 관리 페이지

![Untitled 11](https://user-images.githubusercontent.com/59998914/126848518-ee124621-c43a-4feb-8015-c164ea457a37.png)

## ⏳ Service Flow

![Untitled 12](https://user-images.githubusercontent.com/59998914/126848519-d9298c92-ff3e-4ece-92a6-46740076af09.png)

## 🛠️ Architecture

![Untitled 13](https://user-images.githubusercontent.com/59998914/126848520-68a80af3-3c55-42a3-a512-5f7d62fe860b.png)

## 💭 I Learned

- Express 프레임워크를 활용한 백엔드 개발을 담당하였습니다.
- Paas-ta 클라우드 서비스의 MySQL를 활용하여 사용자 데이터베이스를 구축하였습니다.
- Retrofit을 이용하여 프론트와 통신하였습니다.
- 로그인 페이지 구현을 담당하였습니다.
- 데이터를 받아와 캘린더와 문진표, QR 코드에 추가, 수정하는 기능을 담당하였습니다.