Eric Googlander is a fashion model who performs by walking around on a stage made of squares that form a grid with R rows and C columns.

Eric Googlander는 가로 r, 세로 c 의 그리드로 이루어진 사각형 무대 주위를 걷는 공연을 하는 패션 모델이다.

He begins at the leftmost bottom square, facing toward the top edge of the stage, and he will perform by making a series of moves.

그는 사각형 가장 좌측 하단에서 시작해서 스테이지 상단 가장자리를 향하는 일련의 이동을 공연하려고 한다.

Googlander knows only the following two moves:
Googlander은 다음의 두 동작을 알고있다 :

1. Take one step forward in the direction he is currently facing
현재 향하고 있는 방향으로 한 걸음 움직인다.

2. Make a single 90 degree turn to the right, then take one step forward in the new direction he is facing following the turn
오른쪽으로 90도 회전하고 새로운 방향으로 한걸음 움직인다.

(Note that Googlander does not know how to make a 90 degree left turn.)
(알림: Googlander는 어떻게 좌측으로 90도 회전하는지 알지 못한다.)

If a move would take Googlander off of the stage or onto a square he has already visited, that move is unfashionable.
이동 무대의 해제 또는 그가 이미 방문한 광장에 Googlander을 할 경우, 그 움직임은 유행하지입니다.

Whenever Googlander is in a position for which neither of the two possible moves is unfashionable, he is free to choose either move (independently of any other choices he has made in the past), but he must choose one of them.
Googlander이 두 가지 움직임도가 유행하지되는 위치에있을 때마다, 그는 (독립적으로 자신이 과거에 만든 다른 선택)을 선택 중 하나를 자유롭게 이동할 수있다, 그러나 그는 그들 중 하나를 선택해야합니다.

Whenever one of the possible moves he can make is unfashionable, he must make the other move.
그가 할 수있는 가능한 움직임의 하나가 유행하지 때마다, 그는 다른 이동해야합니다.

If at any point both of the possible moves are unfashionable, the show immediately ends without Googlander making another move.
어떤 시점에서 가능한 이동 모두 유행하지 않은 경우, 쇼는 즉시 Googlander 다른 이동하지 않고 종료합니다.

Note that Googlander cannot stop the show early -- he must keep moving until both possible moves become unfashionable.
그는 모두 가능한 이동 유행하지가 될 때까지 계속 이동합니다 - Googlander 일찍 쇼를 중지 할 수 없습니다.


How many different paths is it possible for Googlander to walk? (Two paths are the same if and only if they visit the same squares in the same order.)

Input

The first line of the input gives the number of test cases, T. T lines follow; each consists of two space-separated integers R and C.
첫번째 줄은 주어진 테스트 케이스의 수 T 이다. 

Output

For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1) and y is the number of different paths that Googlander can walk.

Limits
제한

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
