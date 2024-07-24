#include <iostream>
#include <vector>
#define MAX 1001
using namespace std;

bool isPossible = true;
void dfs(int index, int* arr, vector<int> v[]) {
  for (int i = 0; i < v[index].size(); i++) {
    // cout << "arr[" << index << "]=" << arr[index] << ", arr[" <<
    // v[index][i]<< "]=" << arr[v[index][i]] << "\n";

    if (!arr[v[index][i]]) {
      if (!arr[v[index][i]]) {
        arr[v[index][i]] = 3 - arr[index];
        // cout <<" arr[" << v[index][i]<< "]=" << arr[v[index][i]] << "\n";
        dfs(v[index][i], arr, v);
      }
    }

    if (arr[index] == arr[v[index][i]]) {
      isPossible = false;
    }
    // if(arr[index]==arr[v[index][i]]){
    //     isPossible=false;
    //     return;
    // }
    // if(arr[index]==1){
    //     arr[v[index][i]]=2;
    // }
    // else if(arr[index]==2){
    //     arr[v[index][i]]=1;
    // }
    // dfs(v[index][i],arr,v);
  }
}

int main() {
  int t;
  cin >> t;

  for (int i = 0; i < t; i++) {
    int n, m;
    cin >> n >> m;

    isPossible = true;

    // vector<vector<int>> circle(n+1);
    vector<int> circle[MAX];

    int arr[MAX] = {
        0,
    };

    for (int j = 1; j <= m; j++) {
      int a, b;
      cin >> a >> b;
      circle[a].push_back(b);
      circle[b].push_back(a);
    }

    for (int j = 1; j <= n; j++) {
      if (arr[j] == 0) {
        arr[j] = 1;
        dfs(j, arr, circle);
        // cout << "\n";
      }
    }

    if (isPossible) {
      cout << "possible\n";
    } else {
      cout << "impossible\n";
    }
  }
}