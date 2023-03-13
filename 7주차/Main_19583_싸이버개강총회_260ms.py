import sys
from collections import defaultdict
put = sys.stdin.readlines()
a, b, c = map(int, put[0].replace(":", "").split())
check = defaultdict(int)
answer = 0
for i in range(1, len(put)):
    time, name = put[i].split()
    time = int(time.replace(":", ""))
    if time <= a and check[name] != -1:
        check[name] = 1
    elif b <= time <= c and check[name] == 1:
        check[name] = -1
        answer += 1
print(answer)
