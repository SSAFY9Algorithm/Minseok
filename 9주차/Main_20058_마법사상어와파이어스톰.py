from collections import deque

def devide(y, x, l, depth):
    if l>depth:
        for idx, rotated in enumerate(list(map(list, zip(*[row[x:x+depth*2] for row in arr[y:y+depth*2]][::-1])))):
            arr[y+idx][x:x+depth*2] = rotated
        return
    for i in range(4):
        devide(y+(i//2)*depth, x+(i%2)*depth, l, depth>>1)

def iceBreaking():
    check = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if arr[i][j]:
                for k in range(4):
                    y = i + d[k]
                    x = j + d[(k+1)%4]
                    if 0 <= y < n and 0 <= x < n:
                        check[y][x] += 1
    for i in range(n):
        for j in range(n):
            if check[i][j] < 3 and arr[i][j]:
                arr[i][j] -= 1


def findBigIce():
    visit = [[0 for _ in range(n)] for _ in range(n)]
    kingIce = 0
    for i in range(n):
        for j in range(n):
            if not visit[i][j] and arr[i][j]:
                queue = deque([[i, j]])
                visit[i][j] = 1
                bigIce = 1
                while queue:
                    y, x = queue.popleft()
                    for k in range(4):
                        yy = y + d[k]
                        xx = x + d[(k+1)%4]
                        if 0 <= yy < n and 0 <= xx < n and not visit[yy][xx] and arr[yy][xx]:
                            queue.append([yy, xx])
                            visit[yy][xx] = 1
                            bigIce += 1
                kingIce = max(kingIce, bigIce)
    return kingIce


n, m = map(int, input().split())
n = 1 << n
d = [0, 1, 0, -1]
arr = [[*map(int, input().split())] for _ in range(n)]
L = [*map(int, input().split())]

for l in L:
    devide(0, 0, 1<<l, n)
    iceBreaking()
sumIce = 0
for i in range(n):
    for j in range(n):
        sumIce += arr[i][j]
print(sumIce, findBigIce(), sep='\n')

