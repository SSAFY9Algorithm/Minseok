import sys
from itertools import combinations
from collections import deque
input = sys.stdin.readline

n = int(input())
people = list(map(int, input().split()))
arr = [[] for _ in range(n)]
# 셋으로 부분집합 나눔
st = set()
answer = sys.maxsize
for i in range(n):
    st.add(i)
    temp = list(map(int, input().split()))
    for j in range(temp[0]):
        # 연결요소 저장
        arr[i].append(temp[j+1]-1)

# 절반 크기만 확인
for i in range(1, n//2+1):
    for comb in combinations([_ for _ in range(n)], i):
        # set a, b 분리
        sa = set(comb)
        sb = list(st - sa)
        sa = list(sa)
        visit = [0] * n
        queue = deque([sa[0]])
        visit[sa[0]] = 1
        pa = 0
        # BFS로 setA 체크
        while queue:
            idx = queue.popleft()
            pa += people[idx]
            for x in arr[idx]:
                if not visit[x] and x in sa:
                    visit[x] = 1
                    queue.append(x)
        queue.append(sb[0])
        visit[sb[0]] = 1
        pb = 0
        # BFS로 setB 체크
        while queue:
            idx = queue.popleft()
            pb += people[idx]
            for x in arr[idx]:
                if not visit[x] and x in sb:
                    visit[x] = 1
                    queue.append(x)
        flag = False
        for j in range(n):
            # 모든 지점 방문되었는지 확인
            if not visit[j]:
                flag = True
                break
        if flag:
            continue
        answer = min(answer, abs(pa-pb))
print([answer, -1][answer == sys.maxsize])