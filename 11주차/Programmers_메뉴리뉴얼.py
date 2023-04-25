from collections import defaultdict
from itertools import combinations

# 이거 말고도 푼 방법 있었는데 날아감;
def solution(orders, course):
    answer = []

    # 기본이 0인 map 1부터 10까지 생성
    check = [defaultdict(int) for _ in range(11)]

    for order in orders:
        for c in course:
            for comb in combinations(order, c):
                # 메뉴 조합 만들어서 정렬해서 값 증가시킴
                check[c]["".join(sorted(comb))] += 1
    for i in range(11):
        x = check[i]
        m = 2
        temp = []
        for y in x:
            # 최대값 갱신이면 새로 temp 생성
            if m < check[i][y]:
                m = check[i][y]
                temp = [y]

            # 최대값과 같으면 추가
            elif m == check[i][y]:
                temp.append(y)
        if temp:
            for x in temp:
                answer.append(x)
    answer = sorted(answer)
    return answer