import sys
from collections import deque
input = sys.stdin.readline

def find():
    global bigBlockGroup
    bigBlockGroup = [[], 0]
    visit = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if not visit[i][j] and grid[i][j] > 0:
                bfs(i, j, visit)
    return len(bigBlockGroup[0])

def bfs(i, j, visit):
    queue = deque([[i, j]])
    blockGroup = [[[i, j]], 0]
    visit[i][j] = 1
    color = grid[i][j]
    rainbowBlock = []
    while queue:
        y, x = queue.popleft()
        for i in range(4):
            yy = y + dy[i]
            xx = x + dx[i]
            if 0 <= yy < n and 0 <= xx < n and not visit[yy][xx]:
                if grid[yy][xx] == color or grid[yy][xx] == 0:
                    visit[yy][xx] = 1
                    blockGroup[0].append([yy, xx])
                    queue.append([yy, xx])
                    if grid[yy][xx] == 0:
                        blockGroup[1] += 1
                        rainbowBlock.append([yy, xx])
    bigBlockGroupCheck(blockGroup)
    while rainbowBlock:
        y, x = rainbowBlock.pop()
        visit[y][x] = 0

def bigBlockGroupCheck(blockGroup):
    global bigBlockGroup
    if len(blockGroup[0]) > len(bigBlockGroup[0]):
        bigBlockGroup = blockGroup
    elif len(blockGroup[0]) == len(bigBlockGroup[0]):
        if blockGroup[1] >= bigBlockGroup[1]:
            ay, ax = blockGroup[0][0]
            by, bx = bigBlockGroup[0][0]
            if blockGroup[1] == bigBlockGroup[1]:
                if ay >= by:
                    if ay == by:
                        if ax > bx:
                            bigBlockGroup = blockGroup
                    else:
                        bigBlockGroup = blockGroup
            else:
                bigBlockGroup = blockGroup

def remove():
    global answer
    answer += len(bigBlockGroup[0]) * len(bigBlockGroup[0])
    while bigBlockGroup[0]:
        y, x = bigBlockGroup[0].pop()
        grid[y][x] = -2

def gravity():
    for j in range(n):
        idx = n-1
        for i in reversed(range(n)):
            if grid[i][j] == -1:
                idx = i-1
            elif grid[i][j] >= 0:
                if idx != i:
                    grid[idx][j] = grid[i][j]
                    grid[i][j] = -2
                idx -= 1

def rotate():
    global grid
    grid = list(map(list, zip(*grid)))[::-1]

def play():
    while find() > 1:
        remove()
        gravity()
        rotate()
        gravity()

if __name__ == '__main__':
    n, m = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(n)]
    bigBlockGroup = [[], 0]
    answer = 0
    dy, dx = [0, 1, 0, -1], [1, 0, -1, 0]
    play()
    print(answer)