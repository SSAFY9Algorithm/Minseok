import sys
input = sys.stdin.readline

answer = sys.maxsize

# paper := 남은 종이 확인용
# idx   := 100칸 확인용
# cnt   := 사용한 종이 개수
def check(idx, brr, cnt, paper):
    global answer
    # 최소값 넘었을 때
    if answer < cnt:
        return
    if idx == 100:
        answer = min(answer, cnt)
        return
    y = idx // 10
    x = idx % 10
    if brr[y][x] == 0:
        check(idx+1, brr, cnt, paper)
        return
    if paper[0] > 0:
        brr[y][x] = 0
        paper[0] -= 1
        check(idx+1, brr, cnt+1, paper)
        brr[y][x] = 1
        paper[0] += 1
    # 2~5 사이즈로 가능한지 탐색
    for k in range(2, 6):
        if paper[k-1] == 0:
            continue
        for i in range(k):
            for j in range(k):
                # 체크할 필요 없을 때
                if y+i >= 10 or x+j >= 10 or brr[y+i][x+j] == 0:
                    return
        for i in range(k):
            for j in range(k):
                brr[y+i][x+j] = 0
        paper[k-1] -= 1
        # 이 사이즈의 색종이 붙였을 때 다음 경우의 수 확인
        check(idx+1, brr, cnt+1, paper)
        paper[k-1] += 1
        for i in range(k):
            for j in range(k):
                brr[y+i][x+j] = 1

if __name__ == '__main__':
    arr = [list(map(int, input().split())) for _ in range(10)]
    # [5, 5, 5, 5, 5] : 5장 다 썼는지 확인용
    check(0, arr, 0, [5, 5, 5, 5, 5])
    print([answer, -1][answer == sys.maxsize])