from collections import defaultdict
d, m = map(int, input().split())
D = [*map(int, input().split())]
M = [*map(int, input().split())]

measure = defaultdict(int)
for num in M:
    # 루트까지 약수 확인
    for i in range(1, int(num**0.5)+1):
        if num // i * i == num:
            measure[i] += 1
            measure[num//i] += 1
    # 제곱수인지 확인
    if int(num**0.5) ** 2 == num:
        measure[int(num**0.5)] -= 1
common = []
# 약수 탐색
for num in measure.keys():
    # 모든 수의 약수인 경우
    if measure[num] == m:
        common.append(num)
answer = 0
# 배수 확인
for c in common:
    # 모든 수의 배수인지 확인
    for num in D:
        if c // num * num != c:
            break
    # break 안된 경우
    else:
        answer += 1
print(answer)