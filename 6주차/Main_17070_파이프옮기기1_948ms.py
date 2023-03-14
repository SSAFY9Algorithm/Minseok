import sys
input = sys.stdin.readline
n = int(input())
arr = []
answer = 0
def dfs(y,x,v):
    global answer
    if y == n-1 and x == n-1:
        answer += 1
        return
    if v == 0:
        if x+1 < n and arr[y][x+1] == 0:
            dfs(y,x+1,0)
        if y+1 < n and x+1 < n and arr[y][x+1] == 0 and arr[y+1][x] == 0 and arr[y+1][x+1] == 0:
            dfs(y+1,x+1,1)
    elif v == 1:
        if x+1 < n and arr[y][x+1] == 0:
            dfs(y,x+1,0)
        if y+1 < n and x+1 < n and arr[y][x+1] == 0 and arr[y+1][x] == 0 and arr[y+1][x+1] == 0:
            dfs(y+1,x+1,1)
        if y+1 < n and arr[y+1][x] == 0:
            dfs(y+1,x,2)
    elif v == 2:
        if y+1 < n and x+1 < n and arr[y][x+1] == 0 and arr[y+1][x] == 0 and arr[y+1][x+1] == 0:
            dfs(y+1,x+1,1)
        if y+1 < n and arr[y+1][x] == 0:
            dfs(y+1,x,2)

for i in range(n):
    arr.append(list(map(int,input().split())))
dfs(0,1,0)
print(answer)