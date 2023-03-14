import sys
from copy import deepcopy
from collections import deque

input = sys.stdin.readline

answer = -sys.maxsize

def cal(stack, idx, key):
    global answer
    if idx == n:
        cnt = stack.popleft()
        while stack:
            op = stack.popleft()
            if op == '+':
                cnt += stack.popleft()
            elif op == '-':
                cnt -= stack.popleft()
            else:
                cnt *= stack.popleft()
        answer = max(answer, cnt)
        return

    temp = deepcopy(stack)
    if key:
        if s[idx] == '+':
            stack.append(stack.pop() + int(s[idx+1]))
        elif s[idx] == '-':
            stack.append(stack.pop() - int(s[idx+1]))
        else:
            stack.append(stack.pop() * int(s[idx+1]))
        cal(stack, idx+2, False)
    temp.append(s[idx])
    temp.append(int(s[idx+1]))
    cal(temp, idx+2, True)


if __name__ == '__main__':
    n = int(input())
    s = input()
    cal(deque([int(s[0])]), 1, True)
    print(answer)