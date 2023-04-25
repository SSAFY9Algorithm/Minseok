import sys
from heapq import heappop, heappush
input = sys.stdin.readline

def dijkstra(y, x):
    heap = [[0, y, x]]
    visit = [[int(1e9) for _ in range(n)] for _ in range(n)]
    visit[y][x] = 0
    while heap:
        weight, y, x = heappop(heap)
        if weight > visit[y][x]:
            continue
        for i in range(4):
            yy = y + d[i]
            xx = x + d[i-1]
            if 0 <= yy < n and 0 <= xx < n and visit[yy][xx] > weight + arr[yy][xx]:
                visit[yy][xx] = weight + arr[yy][xx]
                heappush(heap, [visit[yy][xx], yy, xx])
    return visit

# 재귀로 경우의 수 탐색
def check(idx, bit, cnt, val):
    global answer
    if cnt > answer:
        return
    if val == ls:
        cnt += distanceToHome[idx]
        answer = min(answer ,cnt)
    for i in range(ls):
        if bit & (1<<i) == 0:
            if val == 0:
                check(i, bit|(1<<i), cnt+distanceToHome[i], val+1)
            else:
                check(i, bit|(1<<i), cnt+distanceToSoldiers[i][idx], val+1)

n = int(input())
arr = [[*map(int, input().split())] for _ in range(n)]
soldiers = []

d = [0, 1, 0, -1]
for i in range(n):
    for j in range(n):
        if arr[i][j] == -1:
            start = [i, j]
        elif arr[i][j] == 0:
            soldiers.append([i, j])
arr[start[0]][start[1]] = 0
ls = len(soldiers)
if ls == 0:
    print(0)
else:
    # 집과 군인 사이 거리 구하기 위해 집에서 다익
    visit = dijkstra(start[0], start[1])
    # 군인과 집까지의 거리
    distanceToHome = []
    # 군인과 군인 거리, 인접 행렬
    distanceToSoldiers = [[int(1e9) for _ in range(ls)] for _ in range(ls)]
    for i in range(ls):
        y, x = soldiers[i][0], soldiers[i][1]
        # 거리 적용
        distanceToHome.append(visit[y][x])
    for i in range(ls):
        y, x = soldiers[i][0], soldiers[i][1]
        # 군인 위치마다 다익스트라
        visit = dijkstra(y, x)
        for j in range(i+1, ls):
            # 인접 행렬 채우기
            yy, xx = soldiers[j][0], soldiers[j][1]
            distanceToSoldiers[i][j] = visit[yy][xx]
            distanceToSoldiers[j][i] = visit[yy][xx]
    answer = int(1e9)
    check(0, 0, 0, 0)
    print(answer)