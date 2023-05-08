from collections import deque
import sys

sys.setrecursionlimit(3000)


def solution(n, m, x, y, r, c, k):
    def dfs(y, x, v):
        if abs(r - y - 1) + abs(c - x - 1) > k - v:
            return False
        if y == r - 1 and x == c - 1 and v == k:
            return True
        if y + 1 < n:
            string[y + 1][x][v + 1] = string[y][x][v] + "d"
            if dfs(y + 1, x, v + 1):
                return True
        if x - 1 >= 0:
            string[y][x - 1][v + 1] = string[y][x][v] + "l"
            if dfs(y, x - 1, v + 1):
                return True
        if x + 1 < m:
            string[y][x + 1][v + 1] = string[y][x][v] + "r"
            if dfs(y, x + 1, v + 1):
                return True
        if y - 1 >= 0:
            string[y - 1][x][v + 1] = string[y][x][v] + "u"
            if dfs(y - 1, x, v + 1):
                return True

    answer = ''
    dy = [1, 0, 0, -1]
    dx = [0, -1, 1, 0]
    check = k - abs(r - x) - abs(c - y)
    if check < 0 or check % 2:
        return "impossible"
    queue = deque([[x - 1, y - 1, 0]])
    string = [[["" for _ in range(k + 1)] for _ in range(m)] for _ in range(n)]
    dfs(x - 1, y - 1, 0)

    answer = string[r - 1][c - 1][k]
    return answer