import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

# BFS 이용 감염시킴
def infect():
    # 모든 바이러스 경우마다 BFS 돌림 (case[i][y][x] := i번째 바이러스가 y, x 위치에 몇초 뒤에 가는지)
    for i in range(v):
        y, x = viruses[i]
        case[i][y][x] = 0
        queue = deque([viruses[i]])
        while queue:
            y, x = queue.popleft()
            for j in range(4):
                yy = y + d[j]
                xx = x + d[(j+1)%4]
                if 0 <= yy < n and 0 <= xx < n and case[i][yy][xx] > case[i][y][x] + 1:
                    case[i][yy][xx] = case[i][y][x] + 1
                    queue.append([yy, xx])

# 각각 바이러스마다 비교해서 낮은 값만 유지하도록 temp 2차원 배열 생성
def solve():
    answer = sys.maxsize
    # combination 함수
    for comb in combinations([i for i in range(1, v + 1)], m):
        temp = [[sys.maxsize for _ in range(n)] for _ in range(n)]
        for c in comb:
            for i in range(n):
                for j in range(n):
                    temp[i][j] = min(temp[i][j], case[c - 1][i][j])
        key = 0
        for i in range(n):
            for j in range(n):
                if temp[i][j] == sys.maxsize:
                    key = -2
                    break
                key = max(key, temp[i][j])
            if key == -2:
                break
        if key == -2:
            continue
        answer = min(answer, key)
    return answer

# 바이러스는 계산하지 않도록 0으로 변경
def checkVirus():
    for i in range(v):
        for j in range(n):
            for k in range(n):
                if arr[j][k] == 2:
                    case[i][j][k] = 0


if __name__ == '__main__':
    n, m = map(int, input().split())
    arr = [[*map(int, input().split())] for _ in range(n)]
    viruses = []
    [viruses.append([i, j]) if arr[i][j] == 2 else None for i in range(n) for j in range(n)]
    v = len(viruses)
    case = [[[-1 if arr[i][j] == 1 else sys.maxsize for j in range(n)] for i in range(n)] for _ in range(v)]
    d = [0, 1, 0, -1]
    infect()
    checkVirus()
    answer = solve()
    print(answer if answer != sys.maxsize else -1)