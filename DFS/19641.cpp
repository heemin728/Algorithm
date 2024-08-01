#include <algorithm>
#include <iostream>
#include <vector>
#define MAX 100001
using namespace std;

int leftField = 1;
int rightField = 0;
bool visited[MAX];
vector<int> v[MAX];
pair<int, int> answer[MAX];

void dfs(int node) {
  answer[node].first = leftField++;
  // cout << "node " << node << " first = " << answer[node].first << "\n";

  visited[node] = true;

  for (int i = 0; i < v[node].size(); i++) {
    int next = v[node][i];

    if (visited[next]) continue;

    visited[next] = true;

    dfs(next);
  }
  answer[node].second = leftField++;
  // cout << "node " << node << " second = " << answer[node].second << "\n";
}

int main() {
  int N;
  cin >> N;

  for (int i = 0; i < N; i++) {
    int num;
    cin >> num;

    while (true) {
      int node;
      cin >> node;
      if (node == -1) {
        break;
      }

      v[num].push_back(node);
    }
  }

  for (int i = 1; i <= N; i++) {
    sort(v[i].begin(), v[i].end());
  }

  int root;
  cin >> root;

  dfs(root);

  for (int i = 1; i <= N; i++) {
    cout << i << " " << answer[i].first << " " << answer[i].second << "\n";
  }
}

/*
*

   2
 1  3
*/