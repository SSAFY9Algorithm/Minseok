from copy import deepcopy

def select(idx, depth, temp, cnt):
    global answer
    # 이미 도착했을 때
    if temp[idx] == 41:
        return
    # 10번 다 돌렸을 때
    if depth == 10:
        answer = max(answer, cnt)
        return
    # 새로운 배열 만들어서 값 오염 방지
    node = deepcopy(temp)
    diceNum = dice[depth]
    # 시작했을때 다른 경로 있으면 거기로 이동
    if len(arr[node[idx]]) > 1:
        node[idx] = arr[node[idx]][1]
        diceNum -= 1
    # 포인터처럼 이동
    while diceNum:
        node[idx] = arr[node[idx]][0]
        diceNum -= 1
    check = [0] * 42
    # 같은 자리 있는지 체크
    for i in range(4):
        if node[i] and node[i] != 41:
            if check[node[i]]:
                return
            check[node[i]] = 1
    cnt += point[node[idx]]
    for i in range(4):
        select(i, depth+1, node, cnt)


dice = [*map(int, input().split())]
# 이동하기 위한 배열, 다음 idx 저장
arr = [[2], [], [4], [], [6], [], [8], [], [10], [], [12, 11], [13, 13], [14], [15, 15], [16], [25], [18], [], [20], [], [22, 21], [23], [24], [25], [26], [37], [28], [], [30], [], [32, 31], [33], [34], [35], [36], [25], [38], [39], [40], [40], [41], [41], [], [], [], [], [], [], [], []]
# 임의로 번호 변경했기 때문에 point 따로 저장
point = [0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10, 13, 12, 16, 14, 19, 16, 0, 18, 0, 20, 22, 22, 24, 24, 25, 26, 0, 28, 0, 30, 28, 32, 27, 34, 26, 36, 30, 38, 35, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0]
answer = 0
# 처음은 아무데나 시작해도 상관 없음
select(0, 0, [0, 0, 0, 0], 0)
print(answer)
