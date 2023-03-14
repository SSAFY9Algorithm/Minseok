import sys

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    # m := 배열의 크기, c:= 코드 길이, i:= 입력의 크기
    m, c, i = map(int, input().split())
    arr = [0] * m
    brr = [0] * m
    prog = input()
    put = list(map(str, input().strip()))
    put.append(255)
    # program idx, input idx
    pdx, idx = 0, 0
    # 프로그램 카운터
    pc = 0

    stack = []
    loop = False
    while pc < c:
        if prog[pc] == '[':
            cnt = 1
            if arr[pdx] == 0:
                while cnt > 0:
                    pc += 1
                    if prog[pc] == ']':
                        cnt -= 1
                    elif prog[pc] == '[':
                        cnt += 1
            else:
                stack.append([pc, pdx])
                brr[pdx] = arr[pdx]
        elif prog[pc] == ']':
            if arr[pdx] != 0:
                start, sp = stack.pop()
                end, ep = pc, pdx
                if arr[sp] == brr[sp] and arr[ep] == brr[ep]:
                    # 0이 되는지 확인 -> 1에서 2씩 증가하면 0 절대 불가능
                    # (512 - arr[sp]) * n // brr[sp] - arr[sp]
                    # len만큼 더하면서 5000만 넘기전에 되는지 확인
                    # 이라고 생각하는데 여기서 못짜겠음 힘들어

                    loop = True
                    break
                # 5000만 확인
        elif prog[pc] == '<':
            pdx -= 1
        elif prog[pc] == '>':
            pdx = (pdx + 1) % m
        elif prog[pc] == '+':
            arr[pdx] = (arr[pdx] + 1) % 256
        elif prog[pc] == '-':
            arr[pdx] = (arr[pdx] - 1) % 256
        elif prog[pc] == ',':
            brr[pdx] = [ord(put[idx]), 255][put[idx] == 255]
            arr[pdx] = brr[pdx]
            idx = min(idx + 1, i)
        pc = pc + 1

    if loop:
        print("Loops", start, end)
    else:
        print("Terminates")