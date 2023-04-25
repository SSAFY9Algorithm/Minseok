from itertools import accumulate
from collections import defaultdict
n, k = map(int, input().split())
acc = [0] + [*accumulate([*map(int, input().split())])]
# key 접근시 value 0으로 자동 생성되는 Map
dd = defaultdict(int)
answer = 0
for x in acc:
    if x-k in dd.keys():
        answer += dd[x-k]
    dd[x] += 1
print(answer)