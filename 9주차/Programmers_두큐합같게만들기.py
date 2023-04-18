from collections import deque

def solution(queue1, queue2):
    answer = 0
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    s1 = sum(queue1)
    s2 = sum(queue2)
    while s1 != s2 and answer < 200000:
        answer += 1
        if s1 > s2:
            q1 = queue1.popleft()
            queue2.append(q1)
            s1 -= q1
            s2 += q1
        elif s1 < s2:
            q2 = queue2.popleft()
            queue1.append(q2)
            s1 += q2
            s2 -= q2

    return answer if answer != 200000 else -1