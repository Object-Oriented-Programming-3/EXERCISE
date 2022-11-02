## **Commit Rule**

Base format

**YYMMDD 유형(Type) :: 제목(subject)**

```nasm
git commit -m
"220112 Fix :: Template 중 'userDao' 파일 내 sql 쿼리문 수정

sql 쿼리문 - 기존 작성 쿼리에서 트랜잭션 오류 발견 후 변경"
```

Rules for Format

1. 유형(Type) (종류 : 설명)
    - Feat : 새로운 기능 **추가**
    - Fix : 버그 **수정**
    - Style : **스타일** (코드 형식, 코드 정렬, 세미콜론 추가 등 비즈니스 로직에 변경이 없는 경우)
    
2. 제목(Subject)
    - 제목은 20자를 넘기지 x , 마침표는 생략
    - 제목 첫 글자는 대문자 (한글 제외)
    
3. 본문(Body)
    - 최대한 자세하게 작성(commit 기록으로 확인 가능하게끔 작성)
