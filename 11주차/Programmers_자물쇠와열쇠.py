def solution(key, lock):
    # 회전
    def rotate(arr):
        return list(zip(*arr[::-1]))

    # N*N만큼 차 있는지 확인
    def check():
        return sum(sum(brr[i][m:m + n]) for i in range(m, m + n))

    n = len(lock[0])
    m = len(key[0])

    # Padding
    arr = [[0 for _ in range(n + 2 * m)] for _ in range(n + 2 * m)]
    for i in range(n):
        for j in range(n):
            arr[i + m][j + m] = lock[i][j]

    # 실제로 채울 배열 (arr 복사)
    brr = [arr[i][:] for i in range(n + 2 * m)]
    for _ in range(4):
        key = rotate(key)
        for i in range(n + m):
            for j in range(n + m):
                for a in range(m):
                    for b in range(m):
                        # xor로 체크
                        brr[i + a][j + b] ^= key[a][b]
                if check() == n * n:
                    return True

                for a in range(m):
                    for b in range(m):
                        brr[i + a][j + b] = arr[i + a][j + b]
    return False


