import sys
from collections import deque
from heapq import heappop, heappush
input = sys.stdin.readline

# 같은 섬 체크용 (idx로 섬 구분)
def BFS(y, x):
    queue = deque([[y, x]])
    visit[y][x] = 1
    arr[y][x] = idx
    while queue:
        y, x = queue.popleft()
        for k in range(4):
            yy = y + dy[k]
            xx = x + dx[k]
            if 0 <= yy < n and 0 <= xx < m and not visit[yy][xx] and arr[yy][xx] == 1:
                visit[yy][xx] = 1
                queue.append([yy, xx])
                arr[yy][xx] = idx

# 오른쪽, 아래 이어가며 섬과 섬 최소 거리 저장
def cross():
    for i in range(n):
        prev = 0
        cnt = 0
        for j in range(m):
            if arr[i][j] != 0:
                if prev == arr[i][j]:
                    cnt = 0
                    continue
                if prev != 0:
                    if cnt > 1:
                        load[prev][arr[i][j]] = min(load[prev][arr[i][j]], cnt)
                prev = arr[i][j]
                cnt = 0
            else:
                cnt += 1

    for j in range(m):
        prev = 0
        cnt = 0
        for i in range(n):
            if arr[i][j] != 0:
                if prev == arr[i][j]:
                    cnt = 0
                    continue
                if prev != 0:
                    if cnt > 1:
                        load[prev][arr[i][j]] = min(load[prev][arr[i][j]], cnt)
                prev = arr[i][j]
                cnt = 0
            else:
                cnt += 1

def find(num):
    if root[num] != num:
        root[num] = find(root[num])
        return root[num]
    return num


if __name__ == '__main__':
    n, m = map(int ,input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]
    visit = [[0 for _ in range(m)] for _ in range(n)]
    load = [[sys.maxsize for _ in range(8)] for _ in range(8)]
    root = [i for i in range(8)]
    idx = 2
    # idx := 섬 번호
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 1 and not visit[i][j]:
                BFS(i, j)
                idx += 1
    cross()

    heap = []
    for i in range(8):
        for j in range(8):
            if load[i][j] != sys.maxsize:
                heappush(heap, [load[i][j], i, j])
    answer = 0
    # union-find
    while heap:
        v, a, b = heappop(heap)
        fa = find(a)
        fb = find(b)
        if fa == fb:
            continue
        elif fa < fb:
            root[fb] = fa
        else:
            root[fa] = fb
        answer += v

    for i in range(2, idx):
        if find(i) != 2:
            answer = -1
            break

    print(answer)