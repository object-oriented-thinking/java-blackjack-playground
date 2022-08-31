# 블랙잭 미션 구현

## 기능 요구 사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다. 
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다. 
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다. 
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## 이벤트 흐름

1. 참가자의 이름을 등록했다.
2. 참가자들의 배팅 금액을 등록했다.
3. 딜러와 참가자들에게 카드를 나눠줬다.
4. 카드를 확인해 진행여부를 판단했다.
5. 카드를 계속 받을지 딜러와 참여자들에게 물었다.
6. 게임 승패에 따라 분배금을 분배했다.

## 모델링
### 참여자
- 상태
  - 참여자는 이름과 배팅 금액 그리고 카드패를 가지고 있다.
    - 이름은 1 글자 이상이어야 한다.
    - 배팅 금액은 0 이상의 정수여야 한다.
- 행위
  - 카드덱에서 카드를 뽑는다.
  - 자신의 카드패를 확인한다.
  - 자신의 카드패의 합을 확인한다.

### 딜러
- 상태
  - 딜러는 카드패를 가지고 있다.
    - 딜러의 카드패 합이 16 이하이면 카드덱에서 한 장 더 뽑아야 한다.

### 카드패(Cards)
- 상태
  - 카드패에는 카드가 포함되어 있다.
    - 처음 카드패는 최소 두 장의 카드가 존재해야 한다.
- 행위
    - 카드패의 숫자 합을 출력한다.
      - Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
    - 카드패와 숫자 합을 출력준다.

### 카드(Card)
- 상태
  - 카드는 ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10, King, Queen, Jack 중 하나의 숫자를 가진다.
  - 카드덱은 클로버, 하트, 다이아몬드, 스페이드 중 하나의 타입을 가진다.

### 카드덱(OneCard)
- 상태
  - 카드덱에는 52 종류의 카드가 저장되어 있다.
    - 카드덱이 생성되면 카드덱을 섞는다.
- 행위
  - 카드덱에서 카드를 뽑아 건네준다.

### 게임
- 행위
  - 이름 등록
    - 문자열을 받아 이름을 등록한다.
    - 이름은 `,` 단위로 분리한다.
  - 배팅 금액 등록
    - 순서대로 배팅 금액을 등록한다.
    - 배팅 금액은 0 이상의 정수이다.
  - 카드를 모두에게 분배한다.
    - 카드를 참여자와 딜러에게 두 장씩 분배한다.
  - 참여자에게 카드 한 장 분배
    - 카드덱에서 꺼내 참여자에게 카드를 분배한다.
  - 진행 여부 판단
    - 참여자들의 카드를 확인해 진행 여부를 판단한다.
    - 카드 패의 합이 21이 나온 딜러나 참여자가 있다면 게임을 종료한다.
  - 분배금 분배
    - 참여자들과 딜러의 카드 패를 확인한다.
    - 배팅 금액 정보를 기반으로 금액을 분배한다.
