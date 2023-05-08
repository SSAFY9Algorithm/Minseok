def bf(info, ret, index, arrow, lion, apeach):
    global diff
    global answer
    r = ret[:]
    if index == 10 or arrow == 0:
        r[10] += arrow
        d = lion - apeach
        if diff < d:
            diff = d
            answer = r
        if diff != 0 and diff == d:
            for i in range(10,-1,-1):
                if answer[i] > r[i]:
                    break
                elif answer[i] < r[i]:
                    answer = r
                    break
        return
    if arrow >= info[index]+1:
        r[index] += info[index]+1
        bf(info, r, index+1, arrow-info[index]-1, lion+10-index, [apeach,apeach+index-10][info[index] != 0])
    bf(info, ret, index+1, arrow, lion, apeach)
diff = 0
answer = [0]*11

def solution(n, info):
    global answer
    apeach = 0
    for i, v in enumerate(info):
        if v:
            apeach += 10-i
    bf(info, [0]*11, 0, n, 0, apeach)
    if not sum(answer):
        answer = [-1]
    return answer