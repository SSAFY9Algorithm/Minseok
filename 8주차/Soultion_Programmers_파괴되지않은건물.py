def solution(board, skill):
    answer = 0
    n = len(board)
    m = len(board[0])
    prefixSum = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
    for type, sy, sx, ey, ex, degree in skill:
        prefixSum[sy][sx] += degree if type == 2 else -degree
        prefixSum[sy][ex + 1] -= degree if type == 2 else -degree
        prefixSum[ey + 1][sx] -= degree if type == 2 else -degree
        prefixSum[ey + 1][ex + 1] += degree if type == 2 else -degree

    for i in range(n):
        for j in range(m):
            prefixSum[i][j + 1] += prefixSum[i][j]

    for j in range(m):
        for i in range(n):
            prefixSum[i + 1][j] += prefixSum[i][j]

    for i in range(n):
        for j in range(m):
            board[i][j] += prefixSum[i][j]
            if board[i][j] > 0:
                answer += 1

    return answer