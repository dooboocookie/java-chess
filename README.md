# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 요구사항

### 도메인

- 보드
  - [ ] 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
  - [ ] 말을 진영에 맞게 배치한다.
- 말
  - [x] 폰, 룩, 나이트, 비숍, 퀸, 킹을 갖는다.
  - [x] 말은 검은색 혹은 흰색 진영이다.
- 진영
  - [x] 검은색 진영과 흰색 진영이 존재한다.
  - [x] 폰 8개, 룩 2개, 나이트 2개, 비숍 2개, 퀸 1개, 킹 1개가 같은 진영이 된다.

### 입력
- [ ] 실행 시, `start`를 입력 받는다.
- [ ] 종료 시, `end`를 입력 받는다.

### 출력
- [ ] 프로그램 시작 시, 게임 시작에 대한 메세지를 출력한다.
- [ ] `start`를 입력 받으면, 보드판을 출력한다.
- [ ] 폰은 p, 룩은 r, 나이트는 n, 비숍은 b, 퀸은 q, 킹은 k로 표시한다.
- [ ] 검은색 진영은 대문자, 흰색 진영은 소문자로 구분한다.
- [ ] 빈 공간은 . 으로 표현한다.
