#include <iostream>
#include <vector>
#define MAX 101
using namespace std;

void dfs(int index, bool* visited, vector<int> check[]) {
  for (int i = 0; i < check[index].size(); i++) {
    int next = check[index][i];  // 다음 타겟

    if (visited[next]) {
      continue;
    }

    visited[next] = true;
    dfs(next, visited, check);
  }
}

int main() {
  int N, M;

  vector<int> in[MAX];
  vector<int> out[MAX];

  cin >> N >> M;

  for (int i = 0; i < M; i++) {
    int a, b;
    cin >> a >> b;

    in[b].push_back(a);
    out[a].push_back(b);
  }

  for (int i = 1; i <= N; i++) {
    int count = 0;

    bool visited[MAX] = {
        false,
    };
    visited[i] = true;

    // mode 가 0이면 in , 1이면 out
    dfs(i, visited, in);
    dfs(i, visited, out);

    for (int j = 1; j <= N; j++) {
      if (!visited[j]) {
        count++;
      }
    }
    cout << count << "\n";
  }
}
