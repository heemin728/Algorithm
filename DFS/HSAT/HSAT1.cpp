#include <iostream>
#include <vector>
#include <queue>
#define MAX 5
using namespace std;

int n, m;
int map[MAX][MAX];
bool visited[MAX][MAX];
vector<pair<int, int>> v;
int answer = 0;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

bool isInRange(int i, int j)
{
    return i > 0 && j > 0 && i <= n && j <= n;
}

int findWay(int cnt, int endX, int endY, int x, int y)
{

    visited[x][y] = true;
    if (cnt == v.size())
    {
        answer++;
        return 1;
    }

    if (x == endX && y == endY)
    {
        findWay(cnt + 1, v[cnt + 1].first, v[cnt + 1].second, x, y);
    }

    for (int k = 0; k < 4; k++)
    {
        int nx = x + dx[k];
        int ny = y + dy[k];

        if (!isInRange(nx, ny) || visited[nx][ny])
        {
            continue;
        }

        if (map[nx][ny] == 1)
        {
            continue;
        }

        visited[nx][ny] = true;
        findWay(cnt, endX, endY, nx, ny);
        visited[nx][ny] = false;
    }
}

int main(int argc, char **argv)
{

    cin >> n >> m;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> map[i][j];
        }
    }

    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;
        v.push_back({a, b});
    }

    findWay(0, v[1].first, v[1].second, v[0].first, v[0].second);

    cout << answer << "\n";

    return 0;
}