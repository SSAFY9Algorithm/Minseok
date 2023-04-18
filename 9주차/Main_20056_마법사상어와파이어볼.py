def move():
    arr = [[[] for _ in range(n)] for _ in range(n)]
    for y, x, m, s, d in fireBalls:
        yy = (y + s * v[d]) % n
        xx = (x + s * v[(d+2)%8]) % n
        if arr[yy][xx]:
            temp = arr[yy][xx]
            # arr[][][3] := 개수, arr[][][4] := 홀수-짝수 쌍이 있는지
            arr[yy][xx] = [temp[0]+m, temp[1]+s, temp[2], temp[3]+1, max(temp[4], temp[2]%2 != d%2)]
        else:
            arr[yy%n][xx%n] = [m, s, d, 1, 0]
    return split(arr)


def split(arr):
    fireBalls = []
    for i in range(n):
        for j in range(n):
            if arr[i][j]:
                if arr[i][j][3] > 1:
                    m, s = arr[i][j][0]//5, arr[i][j][1]//arr[i][j][3]
                    odd = arr[i][j][4]
                    if m:
                        for k in range(4):
                            fireBalls.append([i, j, m, s, 2*k+odd])
                else:
                    fireBalls.append([i, j, *arr[i][j][:3]])
    return fireBalls


if __name__ == '__main__':
    v = [-1, -1, 0, 1, 1, 1, 0, -1]
    n, m, k = map(int, input().split())
    fireBalls = [[*map(int, input().split())] for _ in range(m)]
    # zero index
    for i in range(m):
        fireBalls[i][0] -= 1
        fireBalls[i][1] -= 1
    for i in range(k):
        fireBalls = move()
    answer = 0
    for _, _, m, _, _ in fireBalls:
        answer += m
    print(answer)

