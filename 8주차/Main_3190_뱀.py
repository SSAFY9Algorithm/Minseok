import sys
input = sys.stdin.readline

def snake(apple, curv):
    state = 0
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]

    hy, hx = 0, 0
    time = 0
    arr = [[0 for _ in range(n)] for _ in range(n)]
    for y, x in apple:
        arr[y-1][x-1] = -1
    arr[0][0] = 1
    while True:
        if curv and time == curv[0][0]:
            if curv[0][1] == 'L':
                state = (state - 1) % 4
            if curv[0][1] == 'D':
                state = (state + 1) % 4
            del curv[0]
        time += 1
        hy += dy[state]
        hx += dx[state]
        if not 0 <= hy < n or not 0 <= hx < n:
            break
        if arr[hy][hx] > 0:
            break
        if arr[hy][hx] == -1:
            arr[hy][hx] = time + 1
        else:
            arr[hy][hx] = time + 1
            m = sys.maxsize
            ty, tx = 0, 0
            queue = [[hy,hx]]
            while queue:
                y = queue[0][0]
                x = queue[0][1]
                del queue[0]
                for i in range(4):
                    yy = y + dy[i]
                    xx = x + dx[i]
                    if 0 <= yy < n and 0 <= xx < n and 0 < arr[yy][xx] < m:
                        queue.append([yy,xx])
                        m = arr[yy][xx]
                        ty = yy
                        tx = xx
            arr[ty][tx] = 0

    return time


if __name__ == '__main__':
    n = int(input())
    k = int(input())
    apple = [list(map(int,input().split())) for _ in range(k)]
    l = int(input())
    curv = []
    for _ in range(l):
        t, v = input().split()
        curv.append([int(t), v])
    print(snake(apple, curv))