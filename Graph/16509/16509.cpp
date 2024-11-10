#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int dx[] = {0, 1, 0, -1}; 
int dy[] = {1, 0, -1, 0};

int cx[] = {-1, 1, 1, -1}; 
int cy[] = {1, 1, -1, -1};

int kr, kc;
bool visited[10][9];

struct Node {
    int x;
    int y;
    int step;
};

bool checkRange(int x, int y) {
    return (0 <= x && x < 10 && 0 <= y && y < 9);
}

void check(queue<Node>& q, Node n) {
    for (int d = 0; d < 4; d++) { 
        int tx = n.x + dx[d];
        int ty = n.y + dy[d];

        if ((tx != kr || ty != kc) && checkRange(tx,ty)) {
            for (int cnt = 0; cnt < 2; cnt++) {
                int s = (d + cnt) % 4;
                int nx = tx + cx[s];
                int ny = ty + cy[s];

                if ((nx != kr && ny != kc) && checkRange(nx,ny)) {
                    nx += cx[s];
                    ny += cy[s];

                    if (checkRange(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        Node n2;
                        n2.x=nx;
                        n2.y=ny;
                        n2.step=n.step+1;
                        q.push(n2);
                    }
                }
            }
        }
    }
}

int bfs(int sr, int sc) {
    queue<Node> q;
    Node n;
    n.x=sr;
    n.y=sc;
    n.step=0;
    q.push(n);
    visited[sr][sc] = true;

    while (!q.empty()) {
        Node now = q.front();
        q.pop();
        if (now.x == kr && now.y == kc) { // 킹 마주치면 종료
            return now.step;
        }
        check(q, now);
    }
    return -1;
}

int main() { 
    int sr, sc;
    cin >> sr >> sc;
    visited[sr][sc] = true;
    cin >> kr >> kc;

    int answer=bfs(sr,sc);
    cout << answer << endl;

    return 0;
}
