import sys
from heapq import heappop, heappush
input = sys.stdin.readline


def dijkstra():
    visit = [[[1e9 for _ in range(m)] for _ in range(n)] for _ in range(3)]
    visit[1][sy-1][sx-1] = 0
    heap = [[0, 1, sy-1, sx-1]]
    while heap:
        weight, num, y, x = heappop(heap)
        if weight > visit[num][y][x]:
            continue
        if num == 0:
            for i in range(4):
                yy = y + d[i]
                xx = x + d[i-1]
                if 0 <= yy < n and 0 <= xx < m and arr[yy][xx] != -1 and visit[1][yy][xx] > weight + arr[yy][xx]:
                    visit[1][yy][xx] = weight + arr[yy][xx]
                    heappush(heap, [visit[1][yy][xx], 1, yy, xx])
        elif num == 1:
            for i in range(2):
                yy = y + d[i*2+1]
                xx = x
                if 0 <= yy < n and 0 <= xx < m and arr[yy][xx] != -1 and visit[2][yy][xx] > weight + arr[yy][xx]:
                    visit[2][yy][xx] = weight + arr[yy][xx]
                    heappush(heap, [visit[2][yy][xx], 2, yy, xx])
        else:
            for i in range(2):
                yy = y
                xx = x + d[i*2+1]
                if 0 <= yy < n and 0 <= xx < m and arr[yy][xx] != -1 and visit[0][yy][xx] > weight + arr[yy][xx]:
                    visit[0][yy][xx] = weight + arr[yy][xx]
                    heappush(heap, [visit[0][yy][xx], 0, yy, xx])
    return min(visit[0][ey-1][ex-1], min(visit[1][ey-1][ex-1], visit[2][ey-1][ex-1]))

n, m = map(int, input().split())
sy, sx, ey, ex = map(int, input().split())
arr = [[*map(int, input().split())] for _ in range(n)]
d = [0, 1, 0, -1]
answer = dijkstra()
print(answer if answer != 1e9 else -1)