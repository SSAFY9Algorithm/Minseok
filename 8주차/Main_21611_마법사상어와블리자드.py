from collections import deque
import sys
input = sys.stdin.readline

def blizard(idx, dep):
    num = 2*idx
    j = num + 9
    for _ in range(dep):
        if len(queue) > num:
            queue[num] = -1
            num = num + j
            j += 8

def boom(queue):
    marbleCnt = 0
    if queue:
        marbleNum = queue[0]
    flag = True
    while flag:
        stack = deque([])
        flag = False
        while queue:
            popNum = queue.popleft()
            if popNum < 1:
                continue
            if marbleNum != popNum:
                if marbleCnt > 3:
                    boomNum[marbleNum] += marbleCnt
                    while marbleCnt:
                        stack.pop()
                        marbleCnt -= 1
                    flag = True
                marbleCnt = 1
            else:
                marbleCnt += 1
            marbleNum = popNum
            stack.append(popNum)
        queue = stack
    return stack



def remake():
    returnQueue = deque([])
    if queue:
        marbleNum = queue[0]
        marbleCnt = 0
    for num in queue:
        if num != marbleNum:
            returnQueue.append(marbleCnt)
            marbleCnt = 0
            if len(returnQueue) >= n * n - 1:
                break
            returnQueue.append(marbleNum)
            marbleNum = num
            if len(returnQueue) >= n * n - 1:
                break
        marbleCnt += 1
    return returnQueue


def mapToQueue():
    bfsQueue = deque([[n//2, n//2, 3]])
    returnQueue = deque([])
    visit = [[0 for _ in range(n)] for _ in range(n)]

    while bfsQueue:
        y, x, v = bfsQueue.popleft()
        visit[y][x] = 1
        checkY = y + dy[(v+1)%4]
        checkX = x + dx[(v+1)%4]
        if 0 <= checkY < n and 0 <= checkX < n and not visit[checkY][checkX]:
            v = (v+1)%4
        nextY = y + dy[v]
        nextX = x + dx[v]
        if 0 <= nextY < n and 0 <= nextX < n:
            returnQueue.append(arr[nextY][nextX])
            bfsQueue.append([nextY, nextX, v])

    return returnQueue

if __name__ == '__main__':
    n, m = map(int, input().split())
    arr = [[*map(int, input().split())] for _ in range(n)]
    dy, dx = [0, 1, 0, -1], [-1, 0, 1, 0]
    changeVec = [-1, 3, 1, 0, 2]
    queue = mapToQueue()
    boomNum = [0, 0, 0, 0]
    for _ in range(m):
        vec, dep = map(int, input().split())
        vec = changeVec[vec]
        blizard(vec, dep)
        queue.append(4)
        queue = boom(queue)
        queue.pop()
        queue.append(4)
        queue = remake()

    answer = 0
    for i in range(4):
        answer += i * boomNum[i]
    print(answer)