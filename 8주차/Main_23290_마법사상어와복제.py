import sys
input = sys.stdin.readline

def moveFish():
    movedGrid = [[[] for _ in range(4)] for _ in range(4)]
    for i in range(4):
        for j in range(4):
            for v in grid[i][j]:
                flag = True
                for _ in range(8):
                    yy = i + dy[v]
                    xx = j + dx[v]
                    if 0 <= yy < 4 and 0 <= xx < 4 and not fishSmellList[yy][xx]:
                        if yy != shark[0] or xx != shark[1]:
                            movedGrid[yy][xx].append(v)
                            flag = False
                            break
                    v = (v+7)%8
                if flag:
                    movedGrid[i][j].append(v)
    moveShark(movedGrid)
    for i in range(4):
        for j in range(4):
            while movedGrid[i][j]:
                grid[i][j].append(movedGrid[i][j].pop())
            if fishSmellList[i][j]:
                fishSmellList[i][j] -= 1

def moveShark(movedGrid):
    global shark
    maxKillFish = -1
    maxKillVector = []
    for sharkVector in sharkVectorCase:
        y = shark[0]
        x = shark[1]
        flag = True
        killFish = 0
        checkFish = [[0 for _ in range(4)] for _ in range(4)]
        for v in sharkVector:
            yy = y + dy[v]
            xx = x + dx[v]
            if 0 <= yy < 4 and 0 <= xx < 4:
                if not checkFish[yy][xx]:
                    killFish += len(movedGrid[yy][xx])
                    checkFish[yy][xx] = 1
                y = yy
                x = xx
            else:
                flag = False
                break
        if flag and maxKillFish < killFish:
            maxKillVector = sharkVector
            maxKillFish = killFish
    y = shark[0]
    x = shark[1]
    for v in maxKillVector:
        yy = y + dy[v]
        xx = x + dx[v]
        if movedGrid[yy][xx]:
            movedGrid[yy][xx] = []
            fishSmellList[yy][xx] = 3
        y = yy
        x = xx
    shark = [y, x]


if __name__ == '__main__':
    dy = [0, -1, -1, -1, 0, 1, 1, 1]
    dx = [-1, -1, 0, 1, 1, 1, 0, -1]
    n, m = map(int, input().split())
    grid = [[[] for _ in range(4)] for _ in range(4)]
    fishSmellList = [[0 for _ in range(4)] for _ in range(4)]
    sharkVectorCase = []
    for i in range(4):
        for j in range(4):
            for k in range(4):
                sharkVectorCase.append([(10-i*2)%8, (10-j*2)%8, (10-k*2)%8])
    for _ in range(n):
        y, x, v = map(int, input().split())
        grid[y-1][x-1].append(v-1)
    y, x = map(int, input().split())
    shark = y-1, x-1
    for _ in range(m):
        moveFish()
    answer = 0
    for i in range(4):
        for j in range(4):
            answer += len(grid[i][j])
    print(answer)