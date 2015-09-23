Eric Googlander is a fashion model who performs by walking around on a stage made of squares that form a grid with R rows and C columns.

Eric Googlander는 패션 모델이 a 그리드 r 로우 c 컬럼으로 이루어진 사각형 무대 주위를 걷는 공연을 하려고 한다.

He begins at the leftmost bottom square, facing toward the top edge of the stage, and he will perform by making a series of moves.

그는 사각형 가장 좌측 하단에서 시작해서 스테이지 상단 모서리를 향하는 일련의 이동을 공연하려고 한다.

Googlander knows only the following two moves:
Googlander은 다음의 두 동작을 알고있다 :

1. Take one step forward in the direction he is currently facing
현재 향하고 있는 정면 방향으로 한 걸음 움직인다.

2. Make a single 90 degree turn to the right, then take one step forward in the new direction he is facing following the turn
오른쪽으로 90도 회전하고 새로운 방향으로 한걸음 움직인다.

(Note that Googlander does not know how to make a 90 degree left turn.)
(알림: Googlander는 좌측으로 90도 회전하는 건 알지 못합니다.)

If a move would take Googlander off of the stage or onto a square he has already visited, that move is unfashionable. Whenever Googlander is in a position for which neither of the two possible moves is unfashionable, he is free to choose either move (independently of any other choices he has made in the past), but he must choose one of them. Whenever one of the possible moves he can make is unfashionable, he must make the other move. If at any point both of the possible moves are unfashionable, the show immediately ends without Googlander making another move. Note that Googlander cannot stop the show early -- he must keep moving until both possible moves become unfashionable.

How many different paths is it possible for Googlander to walk? (Two paths are the same if and only if they visit the same squares in the same order.)

Input

The first line of the input gives the number of test cases, T. T lines follow; each consists of two space-separated integers R and C.

Output

For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1) and y is the number of different paths that Googlander can walk.

Limits

1 ≤ T ≤ 100.
Small dataset

1 ≤ R, C ≤ 10.
The limits ensure that the answer will always fit in a 32-bit signed integer.

Large dataset

1 ≤ R, C ≤ 25.
The limits ensure that the answer will always fit in a 64-bit signed integer.

Sample


Input 
 	
Output 
 
3
1 1
1 3
3 3

Case #1: 1
Case #2: 1
Case #3: 6

In Case #1, Googlander cannot make any moves. The only possible path is the trivial one consisting of the only square.

In Case #2, Googlander cannot take a step straight ahead, because it would take him off the stage, but he can turn right and then take a step. Once he has done so, he cannot turn right and then take a step, but he can take a step straight ahead. At that point, there are no more moves he can make and the show is over. This is the only possible path he can take.

In Case #3, these are the possible paths:
