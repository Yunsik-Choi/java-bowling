# 볼링 게임 점수판
### 볼링 점수판(그리기)
- [x] 플레이어 이름은 영문 3글자이다.
- [x] 넘어진 핀의 수는 0에서 10개 사이의 값이다.
- [x] Ready 상태의 프레임에서 10개 미만의 핀을 쓰러트린 경우, 프레임은 Proceeding 상태가 된다.
- [x] Ready 상태의 프레임에서 10개의 핀을 쓰러트린 경우, 프레임은 Strike 상태가 된다.
- [x] 두번째 투구에서 쓰러트린 핀의 수와 첫번째 투구에서 쓰러트린 핀의 수의 합이 10을 초과하는 경우 예외가 발생한다. 
- [x] 두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트린 경우, 프레임은 Spare 상태가 된다.
- [x] 두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트리지 못한 경우, 프레임은 Miss 상태가 된다.
- [ ] Strike, Spare, Miss 상태에서 투구 시 예외가 발생한다.
- [ ] Strike, Spare, Miss 상태에 맞게 렌더링 할 수 있다. 
- [ ] 마지막 프레임이 스트라이크이거나 스페어이면 한 번 더 투구 할 수 있다. 
